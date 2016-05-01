package fr.istic.sir.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.persistance.db.entities.Person;
import net.persistance.db.tpjpa3.PersonDAOImpl;

@Path("/person")
public class PersonResource {
	PersonDAOImpl personDao = new PersonDAOImpl();
	
	@GET 
    @Produces({ MediaType.APPLICATION_JSON })
	public List<Person> getAll(){
		System.out.println("GetAll() Person");
		return personDao.findAll();
	}
	
	@GET @Path("search/{id}") 
	@Produces({ MediaType.APPLICATION_JSON })
	public Person getById(@PathParam("id") String value){
		return personDao.findById(value);
	}

	@DELETE @Path("delete/{id}") 
	public void deleteById(@PathParam("id") String value) { 
		personDao.delete(value);
	} 

	@GET 
    @Produces(MediaType.TEXT_PLAIN) 
    public String sayPlainTextHello() { 
		return "Hello Jersey"; 
    } 

}
