package lab.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import lab.dao.CountryDao;
import lab.model.Country;

@Repository("countryDao")
public class CountryJpaDaoImpl extends JpaDao implements CountryDao {

	@Override
	public Country save(Country country) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Country savedCountry = em.merge(country);
		transaction.commit();
		if (em != null) {
			em.close();
		}
		return savedCountry;
	}

	@Override
	public List<Country> getCountries() {
		EntityManager em = emf.createEntityManager();
		List<Country> countries = em.createQuery("select c from SimpleCountry c", Country.class)
				.getResultList();
		if (em != null) {
			em.close();
		}
		return countries;
	}

	@Override
	public List<Country> getCountriesStartWith(String name) {
		EntityManager em = emf.createEntityManager();
		List<Country> countriesStartWith = em.createQuery(
				"select c from SimpleCountry c where c.name like :name",
				Country.class)
				.setParameter("name", name + "%")
				.getResultList();
		if (em != null) {
			em.close();
		}
		return countriesStartWith;
	}

	@Override
	public int updateCountryName(String codeName, String newCountryName) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		int rowNum = em.createQuery(
				"update SimpleCountry c set c.name = :name where c.codeName = :code")
				.setParameter("code", codeName)
				.setParameter("name", newCountryName)
				.executeUpdate();
		transaction.commit();
		if (em != null) {
			em.close();
		}
		return rowNum;
	}

	@Override
	public void loadCountries() {
	}

	@Override
	public Country getCountryByCodeName(String codeName) {
		EntityManager em = emf.createEntityManager();
		Country country = em.createQuery(
				"select c from SimpleCountry c where c.codeName like :code",
				Country.class).setParameter("code", codeName).getSingleResult();
		if (em != null) {
			em.close();
		}
		return country;
	}

	@Override
	public Country getCountryByName(String name) {
		EntityManager em = emf.createEntityManager();
		Country country = em.createQuery(
				"select c from SimpleCountry c where c.name like :name", Country.class)
				.setParameter("name", name)
				.getSingleResult();
		if (em != null) {
			em.close();
		}
		return country;
	}

	public int deleteAllCountries(){
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		int delete_from_simpleCountry = em.createQuery(
				"delete from SimpleCountry").executeUpdate();
		transaction.commit();
		if (em != null) {
			em.close();
		}
		return delete_from_simpleCountry;
	}

}
