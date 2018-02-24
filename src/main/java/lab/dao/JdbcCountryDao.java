package lab.dao;

import lab.model.Country;
import lab.model.SimpleCountry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcCountryDao extends NamedParameterJdbcDaoSupport implements CountryDao {

    private static final String GET_ALL_COUNTRIES_SQL = "SELECT id, name, code_name FROM country";
    private static final String GET_COUNTRIES_BY_NAME_SQL = "SELECT id, name, code_name FROM country WHERE name LIKE :name";
    private static final String GET_COUNTRY_BY_NAME_SQL = "SELECT id, name, code_name FROM country WHERE name = '%s'";
    private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "SELECT id, name, code_name FROM country WHERE code_name = '%s'";
    private static final String UPDATE_COUNTRY_NAME_SQL = "UPDATE country SET name='%s' WHERE code_name='%s'";
    private static final String SAVE_COUNTRY_SQL = "INSERT INTO country (name, code_name) VALUES (?, ?)";

    private static final RowMapper<Country> COUNTRY_ROW_MAPPER = (resultSet, i) ->
            new SimpleCountry(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("code_name"));

    @Override
    public List<Country> getCountries() {
        return getJdbcTemplate().query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
    }

    @Override
    public List<Country> getCountriesStartWith(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name + "%");
        return getNamedParameterJdbcTemplate()
                .query(GET_COUNTRIES_BY_NAME_SQL,
                        map,
                        COUNTRY_ROW_MAPPER);
    }

    @Override
    public int updateCountryName(String codeName, String newCountryName) {
        return getJdbcTemplate()
                .update(String.format(UPDATE_COUNTRY_NAME_SQL, newCountryName, codeName));
    }

    @Override
    public void loadCountries() {
        for (String[] countryData : COUNTRY_INIT_DATA)
            save(countryData[0], countryData[1]);
    }

    @Override
    public Country getCountryByCodeName(String codeName) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();

        String sql = String.format(GET_COUNTRY_BY_CODE_NAME_SQL, codeName);

        return jdbcTemplate.query(sql, COUNTRY_ROW_MAPPER).get(0);
    }

    @Override
    public Country getCountryByName(String name)
            throws CountryNotFoundException {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        List<Country> countryList = jdbcTemplate.query(
                String.format(GET_COUNTRY_BY_NAME_SQL, name), COUNTRY_ROW_MAPPER);
        if (countryList.isEmpty()) {
            throw new CountryNotFoundException();
        }
        return countryList.get(0);
    }

    @Override
    public Country save(Country country) {
        assert country.getId() == null;
        return country.setId(
                save(country.getName(), country.getCodeName()));
    }

    public long save(String name, String codeName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            SAVE_COUNTRY_SQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, name);
                    ps.setString(2, codeName);
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int deleteAllCountries() {
        return 0;
    }
}
