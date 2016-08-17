package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenericDAO{
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev3");
	private EntityManager em;

	public EntityManager createEntityManager(){
		em = emf.createEntityManager();
		return em;
	}

	public void closeEntityManager(){
		if (em.isOpen())
		 em.close();
	}
}
