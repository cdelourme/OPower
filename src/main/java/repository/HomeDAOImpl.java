package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.Person;

public class HomeDAOImpl extends GenericDAO{

/*
 * 		createHome(List<Home> homes)
 */
	
	public void createHome(List<Home> homes){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			for(Home p : homes){
				em.persist(p);
			}
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
			tx.rollback();
		}finally{
			closeEntityManager();
		}
	}

/*
 * 		createHome(String nom, int taille, int nombre)
 */
	
	public Home createHome(String nom, int taille, int nombre){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		Home home = null;
			try{
				tx = em.getTransaction();
				tx.begin();	
				home=new Home(nom,taille,nombre);
				em.persist(home);
				tx.commit();
			}catch(Exception re)
			{
				if(tx!=null){
					tx.rollback();
				}
			}finally{
				closeEntityManager();
			}
		return home;
	}

/*
 * 		createHomeHeater(String id, String nom, int consoMoyenne)
 */
	
	public Home createHomeHeater(String id, String nom, int consoMoyenne){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		Home home = findById(id);
			try{
				tx = em.getTransaction();
				tx.begin();
				Heater h = new Heater( nom, consoMoyenne );
				home.addHeater(h);
				em.merge(home);
				tx.commit();
			}catch(Exception re)
			{
				if(tx!=null){
					tx.rollback();
				}
			}finally{
				closeEntityManager();
			}
		return home;
	}

/*
 * 		createHomeDevice(String id, String nom, int consoMoyenne)
 */
	
	public Home createHomeDevice(String id, String nom, int consoMoyenne){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		Home home = findById(id);
			try{
				tx = em.getTransaction();
				tx.begin();
				
				ElectronicDevice h = new ElectronicDevice( nom, consoMoyenne );
				home.addDevice(h);
				em.merge(home);
				tx.commit();
			}catch(Exception re)
			{
				if(tx!=null){
					tx.rollback();
				}
			}finally{
				closeEntityManager();
			}
		return home;
	}

/*
 * 		delete(Home persistentInstance)
 */
	
	public void delete(Home persistentInstance){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			(em.find(Person.class,persistentInstance.getPerson().getId())).removeHome(persistentInstance);
			
			em.remove(em.merge(persistentInstance));
			tx.commit();
		}catch(Exception re)
		{
			re.printStackTrace();
			if(tx!=null){
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
	public List<Home> findAll(){
		EntityManager em = createEntityManager();  
		List<Home> results = null;
		try{
			results = em.createQuery("select p from Home p").getResultList();
		}catch(Exception re)
		{
		}finally{
			closeEntityManager();
		}
		return results;
	}

/*
 * 		findById(String id)
 */
	
	public Home findById(String id){
		EntityManager em = createEntityManager();  
		Home instance = null;
		try{
			instance = em.find(Home.class, id);
		}catch (RuntimeException re){
		throw re;
		}finally{
			closeEntityManager();
		}
		return instance;
	}
	
/*
 * 		findByPersonId(String id)
 */
	
	public List<Home> findByPersonId(String id){
		EntityManager em = createEntityManager();  
		Person instance = null;
		List<Home> homes = null;
		try{
			instance = em.find(Person.class, id);
			if (instance != null)
				homes = instance.getHomes();
		}catch (RuntimeException re){
		throw re;
		}finally{
			closeEntityManager();
		}
		return homes;
	}

/*
 * 		update (Home p)
 */
	
	public Home update (Home p) {
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
				tx.rollback();
			}
		}finally{
			closeEntityManager();
		}
		return p;
	}
}
