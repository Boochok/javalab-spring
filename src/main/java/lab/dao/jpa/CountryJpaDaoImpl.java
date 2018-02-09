package lab.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lab.dao.CountryDao;
import lab.model.Country;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

	@Override
	public void save(Country country) {
//		TODO: Implement it
		EntityManager em = null;

		if (em != null) {
			em.close();
		}
	}

	@Override
	public List<Country> getCountries() {
//	TODO: Implement it
		return null	;
	}// getAllcountries()

	@Override
	public List<Country> getCountriesStartWith(String name) {
		return null;
	}

	@Override
	public void updateCountryName(String codeName, String newCountryName) {

	}

	@Override
	public void loadCountries() {

	}

	@Override
	public Country getCountryByCodeName(String codeName) {
		return null;
	}

	@Override
	public Country getCountryByName(String name) {
//		TODO: Implement it

		return null;
	}

}
