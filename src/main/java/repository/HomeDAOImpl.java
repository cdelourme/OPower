package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.Heater;
import domain.Home;
import domain.Person;

public class HomeDAOImpl extends GenericDAO{
	
	public void createHome(List<Home> homes){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			
			for(Home p : homes){
				System.out.println("Ajout de Home " + p.toString());
				em.persist(p);
			}
			
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
			System.out.println("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
			closeEntityManager();
		}
	}

	public Home createHome(String nom, int taille, int nombre){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		Home home = null;
			try{
				tx = em.getTransaction();
				tx.begin();
				
				home=new Home(nom,taille,nombre);
			
				System.out.println("Create a Home" + home.toString());
				em.persist(home);
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
		return home;
	}
	
	public Home createHomeHeater(String id, String nom, int consoMoyenne){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		Home home = findById(id);
			try{
				tx = em.getTransaction();
				tx.begin();
				
				Heater h = new Heater( nom, consoMoyenne );
				home.addHeater(h);
			
				System.out.println("Create a Home" + home.toString());
				em.merge(home);
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
		return home;
	}
	
	public void delete(Home persistentInstance){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			em.remove(persistentInstance);//em.merge(persistentInstance));
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
	
	public void delete(String id){
		delete(findById(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<Home> findAll(){
		EntityManager em = createEntityManager();  
		List<Home> results = null;
		try{
		
			results = em.createQuery("select p from Home p").getResultList();
			
		}catch(Exception re)
		{
			System.out.println("Something went wrong; Discard all partial changes");
		}finally{
			closeEntityManager();
		}
		System.out.println("sortie de getALL()");
		return results;
	}

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
				System.out.println("Something went wrong; Discard all partial changes");
				tx.rollback();
			}
		}finally{
			closeEntityManager();
		}
		return p;
	}
}
