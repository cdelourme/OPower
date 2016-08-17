package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.Heater;
import domain.Home;

public class HeaterDAOImpl extends GenericDAO{
	
/*
 * 		createHeater(String name, int conso)
 */
	
	public Heater createHeater(String name, int conso){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		Heater heater = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			heater=new Heater(name,conso);
			em.persist(heater);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null){
				System.out.println("Something went wrong; Discard all partial changes");
				tx.rollback();
			}
		}finally{
			closeEntityManager();
		}
		return heater;
	}
	
/*
 * 		delete(Heater persistentInstance)
 */
	
	public void delete(Heater persistentInstance){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			(em.find(Home.class,persistentInstance.getHome().getId())).removeHeater(persistentInstance);
			em.remove(em.merge(persistentInstance));
			tx.commit();
		}catch(Exception re)
		{
			re.printStackTrace();
			if(tx!=null){
				System.out.println("Something went wrong; Discard all partial changes");
				tx.rollback();
			}
		}finally{
			closeEntityManager();
		}
	}

/*
 * 		delete(String id)
 */
	
	public void delete(String id){
		delete(findById(id));
	}

/*
 * 		findAll()
 */
	
	@SuppressWarnings("unchecked")
	public List<Heater> findAll(){
		EntityManager em = createEntityManager();  
		List<Heater> results = null;
		try{
			results = em.createQuery("select h from Heater h").getResultList();
		}catch(Exception re)
		{
			System.out.println("Something went wrong; Discard all partial changes");
		}finally{
			closeEntityManager();
		}
		System.out.println("sortie de getALL()");
		return results;
	}

/*
 * 		findById(String id)
 */
	
	public Heater findById(String id){
		EntityManager em = createEntityManager();  
		Heater instance = null;
		try{
			instance = em.find(Heater.class, id);
		}catch (RuntimeException re){
		throw re;
		}finally{
			closeEntityManager();
		}
		return instance;
	}

/*
 * 		update (Heater p)
 */
	
	public Heater update (Heater p) {
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			em.merge(p);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null){
				System.out.println("Something went wrong; Discard all partial changes");
				tx.rollback();
			}
		}finally{
			closeEntityManager();
		}
		return p;
	}
}
