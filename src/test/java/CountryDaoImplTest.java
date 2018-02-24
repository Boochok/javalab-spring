import lab.dao.CountryDao;
import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:orm.xml")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CountryDaoImplTest {

    private static final Country EXAMPLE_COUNTRY = new SimpleCountry(1L, "Australia", "AU");

    final CountryDao countryDao;

    @NonFinal
    int size;


    @Test
    void testSaveCountry() {
        countryDao.save(EXAMPLE_COUNTRY);
        size++;
        List<Country> countries = countryDao.getCountries();
        assertEquals(1, countries.size());
        assertEquals(EXAMPLE_COUNTRY, countries.get(0));
    }

    @Test
    void testGetAllCountries() {
        countryDao.save(new SimpleCountry(1L, "Canada", "CA"));
        size++;
        List<Country> countries = countryDao.getCountries();
        assertEquals(size, countries.size());
    }

    @Test
    void testGetCountryByCodeName(){
        countryDao.save(new SimpleCountry(1L, "Australia", "AU"));
        size++;
        Country country = countryDao.getCountryByCodeName("AU");
        assertEquals(EXAMPLE_COUNTRY, country);
    }

    @Test
    void updateCountry(){
        countryDao.save(new SimpleCountry(1L, "Russia", "RU"));
        size++;
        List<Country> countries = countryDao.getCountriesStartWith("R");
        assertEquals("Russia", countries.get(0).getName());
        countryDao.updateCountryName("RU", "Russian Federation");
        countries = countryDao.getCountries();
        assertNotEquals("Russia", countries.get(0).getName());
    }
}
