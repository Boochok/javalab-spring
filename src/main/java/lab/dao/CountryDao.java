package lab.dao;

import java.util.List;

import lab.model.Country;

import lab.model.SimpleCountry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class CountryDao extends NamedParameterJdbcDaoSupport {

	private static final String LOAD_COUNTRIES_SQL = "INSERT INTO country (name, code_name) VALUES ('%s', '%s')";

	private static final String GET_ALL_COUNTRIES_SQL = "SELECT * FROM country";
	private static final String GET_COUNTRIES_BY_NAME_SQL = "SELECT * FROM country WHERE name LIKE :name";
	private static final String GET_COUNTRY_BY_NAME_SQL = "SELECT * FROM country WHERE name = '%s'";
	private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "SELECT * FROM country WHERE code_name = '%s'";

	private static final String UPDATE_COUNTRY_NAME_SQL = "UPDATE country SET name='%s' WHERE code_name='%s'";

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String CODE_NAME = "code_name";

	public static final String[][] COUNTRY_INIT_DATA = {
			{ "Australia", "AU" },
			{ "Canada", "CA" },
			{ "France", "FR" },
			{ "Hong Kong", "HK" },
			{ "Iceland", "IC" },
			{ "Japan", "JP" },
			{ "Nepal", "NP" },
			{ "Russian Federation", "RU" },
			{ "Sweden", "SE" },
			{ "Switzerland", "CH" },
			{ "United Kingdom", "GB" },
			{ "United States", "US" } };

	private static final RowMapper<Country> COUNTRY_ROW_MAPPER = (resultSet, i)->
			new SimpleCountry(
					resultSet.getInt(ID),
					resultSet.getString(NAME),
					resultSet.getString(CODE_NAME));


	public List<Country> getCountryList() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
	}

	public List<Country> getCountryListStartWith(String name) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				getDataSource());
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
				"name", name + "%");
		return namedParameterJdbcTemplate.query(GET_COUNTRIES_BY_NAME_SQL,
				sqlParameterSource, COUNTRY_ROW_MAPPER);
	}

	public void updateCountryName(String codeName, String newCountryName) {
		getJdbcTemplate().execute(String.format(UPDATE_COUNTRY_NAME_SQL, newCountryName, codeName));
	}

	public void loadCountries() {
		for (String[] countryData : COUNTRY_INIT_DATA) {
			getJdbcTemplate().execute(
					String.format(LOAD_COUNTRIES_SQL, countryData[0], countryData[1]));
		}
	}

	public Country getCountryByCodeName(String codeName) {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();

		String sql = String.format(GET_COUNTRY_BY_CODE_NAME_SQL, codeName);

		return jdbcTemplate.query(sql, COUNTRY_ROW_MAPPER).get(0);
	}

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
}
