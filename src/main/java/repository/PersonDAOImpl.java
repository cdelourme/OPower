package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.Home;
import domain.Person;

public class PersonDAOImpl extends GenericDAO{
	
	public void createPerson(List<Person> persons){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			
			for(Person p : persons){
				System.out.println("Ajour de la Personne " + p.toString());
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
	
	public Person createPerson(String name, String forename, String email){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		Person person = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			person=new Person(name,forename,email);
			System.out.println("Create a User" + person.toString());
			em.persist(person);
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
		return person;
	}
	
	public Person updatePersonHome(String id, String name, int taille, int nombrePiece){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		Person person = findById(id);
		try{
			tx = em.getTransaction();
			tx.begin();
			Home h = new Home(name,taille,nombrePiece);
			person.addHome(h);
			System.out.println("Update User/home" + person.toString());
			em.merge(person);
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
		return person;
	}

	public void delete(Person persistentInstance){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
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
	
	public void delete(String id){
		delete(findById(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> findAll(){
		EntityManager em = createEntityManager();  
		List<Person> results = null;
		try{
		
			results = em.createQuery("select p from Person p").getResultList();
			
		}catch(Exception re)
		{
			System.out.println("Something went wrong; Discard all partial changes");
		}finally{
			closeEntityManager();
		}
		System.out.println("sortie de getALL()");
		return results;
	}

	public Person findById(String id){
		EntityManager em = createEntityManager();  
		Person instance = null;
		try{
			instance = em.find(Person.class, id);
		}catch (RuntimeException re){
		throw re;
		}finally{
			closeEntityManager();
		}
		
		return instance;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> findByMail(String mail){
		EntityManager em = createEntityManager();  
		List<Person> results = null;
		try{	
			results = (em.createQuery("select p from Person p WHERE p.mail LIKE :custName").setParameter("custName", mail)).getResultList();
			
		}catch(Exception re)
		{
			System.out.println("Something went wrong; Discard all partial changes : " + re.getMessage());
		}finally{
			closeEntityManager();
		}
		System.out.println("sortie de findByMail()");
		return results;
	}

	
	public Person update (Person p) {
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
