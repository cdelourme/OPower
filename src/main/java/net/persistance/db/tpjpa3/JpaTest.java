package net.persistance.db.tpjpa3;

import net.persistance.db.entities.ElectronicDevice;
import net.persistance.db.entities.Heater;
import net.persistance.db.entities.Home;
import net.persistance.db.entities.Person;
public class JpaTest {
/**
* @param args
*/
public static void main(String[] args) {
	
		Heater heat1 = new Heater("Salon",1000);
		Heater heat2 = new Heater("Cuisine",750);
		
		ElectronicDevice ed = new ElectronicDevice("SecheLinge",5000);
		ElectronicDevice ed2 = new ElectronicDevice("Fraiseuse",10000);
		
		Home h1 = new Home("Chateau",250,7);
		Home h2 = new Home("La banque",1120,14);
		
		h1.addHeater(heat1);
		h1.addHeater(heat2);
		
		h1.addDevice(ed);
		h1.addDevice(ed2);
		
		PersonDAOImpl persDao = new PersonDAOImpl();
		Person p = persDao.createPerson("Oncle","Picsou","oncle.picsou@flagada.jones");
		p.setNom("Donald");
				
		p.addHome(h1);
		p.addHome(h2);
		
		persDao.update(p);
	}
}