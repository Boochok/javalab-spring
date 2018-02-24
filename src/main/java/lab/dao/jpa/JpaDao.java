package lab.dao.jpa;

import lombok.Setter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public abstract class JpaDao {

	@Setter(onMethod = @__(@PersistenceUnit))
	protected EntityManagerFactory emf;
}