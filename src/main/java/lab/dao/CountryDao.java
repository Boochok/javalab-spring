package lab.dao;

import java.util.List;

import lab.model.Country;

public interface CountryDao {

    String[][] COUNTRY_INIT_DATA = {
            {"Australia", "AU"},
            {"Canada", "CA"},
            {"France", "FR"},
            {"Hong Kong", "HK"},
            {"Iceland", "IC"},
            {"Japan", "JP"},
            {"Nepal", "NP"},
            {"Russian Federation", "RU"},
            {"Sweden", "SE"},
            {"Switzerland", "CH"},
            {"United Kingdom", "GB"},
            {"United States", "US"}};

    List<Country> getCountries();

    List<Country> getCountriesStartWith(String name);

    int updateCountryName(String codeName, String newCountryName);

    void loadCountries();

    Country getCountryByCodeName(String codeName);

    Country getCountryByName(String name) throws CountryNotFoundException;

    Country save(Country country);

    int deleteAllCountries();
}
