package lab.service;

import lab.dao.CountryDao;
import lab.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

//@Repository is more convenient declaration for such a class than general @Service
@Repository
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

	private CountryDao jdbcCountryDao;

	public List<Country> getAllCountriesInsideTransaction(Propagation propagation) {

		if (Propagation.REQUIRED.equals(propagation)) {
			return getAllCountriesRequired();
		} else if (Propagation.REQUIRES_NEW.equals(propagation)) {
			return getAllCountriesRequiresNew();
		} else if (Propagation.SUPPORTS.equals(propagation)) {
			return getAllCountriesSupports();
		} else if (Propagation.NEVER.equals(propagation)) {
			return getAllCountriesNever();
		} else if (Propagation.MANDATORY.equals(propagation)) {
			return getAllCountriesMandatory();
		} else if (Propagation.NOT_SUPPORTED.equals(propagation)) {
			return getAllCountriesNotSupported();
		} else {
			return getAllCountries();
		}
	}

	public List<Country> getAllCountriesRequired() {
		return jdbcCountryDao.getCountries();
	}

	public List<Country> getAllCountriesRequiresNew() {
		return jdbcCountryDao.getCountries();
	}

	public List<Country> getAllCountriesSupports() {
		return jdbcCountryDao.getCountries();
	}

	public List<Country> getAllCountriesNever() {
		return jdbcCountryDao.getCountries();
	}

	public List<Country> getAllCountriesMandatory() {
		return jdbcCountryDao.getCountries();
	}

	public List<Country> getAllCountriesNotSupported() {
		return jdbcCountryDao.getCountries();
	}

	public List<Country> getAllCountries() {
		return jdbcCountryDao.getCountries();
	}

	public CountryDao getJdbcCountryDao() {
		return jdbcCountryDao;
	}

	public void setJdbcCountryDao(CountryDao jdbcCountryDao) {
		this.jdbcCountryDao = jdbcCountryDao;
	}

}