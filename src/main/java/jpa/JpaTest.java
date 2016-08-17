package jpa;

import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.Person;
import repository.PersonDAOImpl;
public class JpaTest {
/**
* @param args
*/
public static void main(String[] args) {
	
		Heater heat1 = new Heater("Salon",2000);
		Heater heat2 = new Heater("Cuisine",750);
		Heater heat3 = new Heater("Chambre",1000);
		Heater heat4 = new Heater("Entrée",750);
		Heater heat5 = new Heater("Salon",1000);
		Heater heat6 = new Heater("Cuisine",750);
		Heater heat7 = new Heater("Salon",2000);
		Heater heat8 = new Heater("Cuisine",750);
		Heater heat9 = new Heater("Chambre",1000);
		Heater heat10 = new Heater("Entrée",750);
		
		ElectronicDevice ed1 = new ElectronicDevice("SecheLinge",5000);
		ElectronicDevice ed2 = new ElectronicDevice("Fraiseuse",6000);
		ElectronicDevice ed3 = new ElectronicDevice("Halogène",1000);
		ElectronicDevice ed4 = new ElectronicDevice("Compresseur",12000);
		ElectronicDevice ed5 = new ElectronicDevice("Congélateur",2000);
		ElectronicDevice ed6 = new ElectronicDevice("Téléviseur",700);
		
		Home h1 = new Home("Chateau",750,20);
		Home h2 = new Home("La banque",1120,14);
		Home h3 = new Home("Pavillon",100,5);
		Home h4 = new Home("Chalet",75,3);
		Home h5 = new Home("Pavillon 2",250,7);
		Home h6 = new Home("Cabane",8,1);
		
		PersonDAOImpl persDao = new PersonDAOImpl();
		Person p = persDao.createPerson("Oncle","Picsou","oncle.picsou@flagada.jones");
		Person p1 = persDao.createPerson("Oncle","Donald","oncle.donald@flagada.jones");
		Person p2 = persDao.createPerson("Riri","Jones","riri@castor.junior");
		Person p3 = persDao.createPerson("Fifi","Jones","fifi@castor.junior");
		Person p4 = persDao.createPerson("Loulou","Jones","loulou@castor.junior");

//	***	oncle.picsou@flagada.jones ***
		
		h1.addHeater(heat1);
		h1.addHeater(heat2);
		h1.addDevice(ed1);
		h1.addDevice(ed2);
		
		h2.addHeater(heat3);
		h2.addHeater(heat4);
		h2.addDevice(ed3);
		h2.addDevice(ed4);
		
		p.addHome(h1);
		p.addHome(h2);
		persDao.update(p);
		
//	***	oncle.donald@flagada.jones ***
		
		h3.addHeater(heat5);
		h3.addHeater(heat6);
		p1.addHome(h3);
		persDao.update(p1);

//	***	riri@castor.junior ***
	
		h4.addHeater(heat7);
		p2.addHome(h4);
		persDao.update(p2);

//	***	fifi@castor.junior ***
	
		p3.addHome(h5);
		h5.addDevice(ed5);
		h5.addDevice(ed6);
		persDao.update(p3);
		
//	***	loulou@castor.junior ***
		
		h6.addHeater(heat8);
		h6.addHeater(heat9);
		h6.addHeater(heat10);
		p4.addHome(h6);
		persDao.update(p4);		
	}
}