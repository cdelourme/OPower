package web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Person;
import repository.PersonDAOImpl;

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
		System.out.println("DELETE");
		Person p2 = personDao.findById(value);
		if (p2 != null)
			personDao.delete(p2);		
	} 

/*	@GET 
    @Produces(MediaType.TEXT_PLAIN) 
    public String sayPlainTextHello() { 
		return "Hello Jersey"; 
    } */
	
	@POST 
    public void createPerson(@FormParam("nom") String nom,
            @FormParam("prenom") String prenom,
            @FormParam("mail") String mail) { 
		System.out.println("POST de Person"); 
		System.out.println(nom +","+ prenom +","+ mail); 
		//on s'arrure de l'unicité des mails
		if (personDao.findByMail(mail).size() == 0)
			personDao. createPerson(nom,prenom,mail);
    } 
	
	@POST @Path("{id}/") 
    public void updatePersonHome(@PathParam("id") String id,@FormParam("nom") String nom,
            @FormParam("taille") int taille,
            @FormParam("nombrePiece") int nombrePiece) { 
		System.out.println("POST de Person/home" + id); 
		System.out.println(nom +","+ taille +","+ nombrePiece); 
		//on s'arrure de l'unicité des mails
		personDao.updatePersonHome(id,nom,taille,nombrePiece);
    } 
	
	@PUT
    @Consumes({ MediaType.APPLICATION_JSON }) 
    public void createUpdatePerson( Person p) { 
		System.out.println("PUT de Person ...");
		Person p2 = personDao.findById(p.getId());
		if (p2 != null){
			System.out.println("		-> Update");
			p2=p;
			personDao.update(p2);
		}
		else
			System.out.println("		-> Creation");
			// PB : est appelé après un update !!!
			//personDao. createPerson(p.getNom(),p.getPrenom(),p.getMail());
    } 
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON }) 
    public void createPerson( Person p) { 
		personDao.createPerson(p.getNom(),p.getPrenom(),p.getMail());
    } 
}
