package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.Person;

public class DeviceDAOImpl extends GenericDAO{
	
	public ElectronicDevice createDevice(String name, int conso){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		ElectronicDevice device = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			device=new ElectronicDevice(name,conso);
			System.out.println("Create an ElectronicDevice" + device.toString());
			em.persist(device);
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
		return device;
	}
	
	
	public void delete(ElectronicDevice persistentInstance){
		EntityManager em = createEntityManager();  
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			(em.find(Home.class,persistentInstance.getHome().getId())).removeDevice(persistentInstance);
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
	public List<ElectronicDevice> findAll(){
		EntityManager em = createEntityManager();  
		List<ElectronicDevice> results = null;
		try{
		
			results = em.createQuery("select d from ElectronicDevice d").getResultList();
			
		}catch(Exception re)
		{
			System.out.println("Something went wrong; Discard all partial changes");
		}finally{
			closeEntityManager();
		}
		System.out.println("sortie de getALL()");
		return results;
	}

	public ElectronicDevice findById(String id){
		EntityManager em = createEntityManager();  
		ElectronicDevice instance = null;
		try{
			instance = em.find(ElectronicDevice.class, id);
		}catch (RuntimeException re){
		throw re;
		}finally{
			closeEntityManager();
		}
		
		return instance;
	}
		
	public ElectronicDevice update (ElectronicDevice p) {
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
