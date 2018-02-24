package jdbc;

import lab.dao.CountryDao;
import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:jdbc.xml")
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcTest{

	private CountryDao jdbcCountryDao;
	
    private List<Country> expectedCountryList = new ArrayList<Country>();
    private List<Country> expectedCountryListStartsWithA = new ArrayList<>();
    private Country countryWithChangedName = new SimpleCountry(8L, "Russia", "RU");

    @BeforeEach
    public void setUp() throws Exception {
        initExpectedCountryLists();
        jdbcCountryDao.loadCountries();
    }

    
    @Test
    @DirtiesContext
    void testCountryList() {
        List<Country> countries = jdbcCountryDao.getCountries();
        assertNotNull(countries);
        assertEquals(expectedCountryList.size(), countries.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countries.get(i));
        }
    }

    @Test
    @DirtiesContext
    void testCountryListStartsWithA() {
        List<Country> countriesA = jdbcCountryDao.getCountriesStartWith("A");
        assertNotNull(countriesA);
        assertEquals(expectedCountryListStartsWithA.size(), countriesA.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertEquals(expectedCountryListStartsWithA.get(i), countriesA.get(i));
        }
    }

    @Test
    @DirtiesContext
    void testCountryChange() {
        jdbcCountryDao.updateCountryName("RU", "Russia");
        assertEquals(countryWithChangedName, jdbcCountryDao.getCountryByCodeName("RU"));
    }

    private void initExpectedCountryLists() {
        for (int i = 0; i < CountryDao.COUNTRY_INIT_DATA.length;) {
            String[] countryInitData = CountryDao.COUNTRY_INIT_DATA[i++];
            Country country = new SimpleCountry((long)  i, countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }
}