package ca.gc.ic.cipo.core.dao.test;

import static org.junit.Assert.assertEquals;

import ca.gc.ic.cipo.core.dao.dto.test.AddressDTO;
import ca.gc.ic.cipo.core.dao.dto.test.HomeDTO;
import ca.gc.ic.cipo.core.dao.dto.test.PersonDTO;
import ca.gc.ic.cipo.core.dao.model.test.Address;
import ca.gc.ic.cipo.core.dao.model.test.Home;
import ca.gc.ic.cipo.core.dao.model.test.Person;

public abstract class DTOBaseTest {

	protected Person p1;
	protected Person p2;
	protected PersonDTO pdto1;
	protected PersonDTO pdto2;
	
	protected void setupObjects() {
		
		Address a1 = new Address("165 Hotêl de ville", "Gatineau", "Québec", "K1A 0C9");
		Address a2 = new Address("50 rue Victoria", "Gatineau", "Québec", "K1A 0C9");
		Home h1 = new Home("Office", a1);
		Home h2 = new Home("Office", a2);
		p1 = new Person("The old", "Employee", 55, h1);
		p2 = new Person("The young", "Consultant", 20, h2);
			
		AddressDTO adto1 = new AddressDTO("165 Hotêl de ville", "Gatineau", "Québec", "K1A 0C9");
		AddressDTO adto2 = new AddressDTO("50 rue Victoria", "Gatineau", "Québec", "K1A 0C9");
		HomeDTO hdto1 = new HomeDTO("Office", adto1);
		HomeDTO hdto2 = new HomeDTO("Office", adto2);
		pdto1 = new PersonDTO("The old", "Employee", 55, hdto1);
		pdto2 = new PersonDTO("The young", "Consultant", 20, hdto2);
	}	

	protected void print(PersonDTO p, int pNum) {
		
		System.out.println("");
		System.out.println("*****************************************************");
		System.out.println("Creating Person DTO...");
		System.out.println("Person DTO " + pNum + " = " + p);
		System.out.println("Person DTO home " + pNum + " = " + p.getHome());
		System.out.println("Person DTO address " + pNum + " = " + p.getHome().getAddress());		
	}

	protected void print(Person p, int pNum) {
		System.out.println("");
		System.out.println("*****************************************************");
		System.out.println("Creating Person...");
		System.out.println("Person " + pNum + " = " + p);
		System.out.println("Person home " + pNum + " = " + p.getHome());
		System.out.println("Person address " + pNum + " = " + p.getHome().getAddress());
	}	
	
	protected Boolean compareAddress(AddressDTO dto, Address a) {
		assertEquals(dto.getAddressId(), a.getId());
		assertEquals(dto.getStreet(), a.getStreet());
		assertEquals(dto.getCity(), a.getCity());
		assertEquals(dto.getState(), a.getState());
		assertEquals(dto.getZip(), a.getZip());		
		return true;
	}
	
	protected Boolean compareAddress(Address a1, Address a2) {
		assertEquals(a1.getId(), a2.getId());
		assertEquals(a1.getStreet(), a2.getStreet());
		assertEquals(a1.getCity(), a2.getCity());
		assertEquals(a1.getState(), a2.getState());
		assertEquals(a1.getZip(), a2.getZip());		
		return true;
	}

	protected Boolean compareAddress(AddressDTO a1, AddressDTO a2) {
		assertEquals(a1.getAddressId(), a2.getAddressId());
		assertEquals(a1.getStreet(), a2.getStreet());
		assertEquals(a1.getCity(), a2.getCity());
		assertEquals(a1.getState(), a2.getState());
		assertEquals(a1.getZip(), a2.getZip());		
		return true;
	}

	protected Boolean compareHome(HomeDTO dto, Home h) {
		assertEquals(dto.getHomeId(), h.getId());
		assertEquals(dto.getType(), h.getType());		
		compareAddress(dto.getAddress(), h.getAddress());		
		return true;
	}

	protected Boolean compareHome(Home h1, Home h2) {
		assertEquals(h1.getId(), h2.getId());
		assertEquals(h1.getType(), h2.getType());		
		compareAddress(h1.getAddress(), h2.getAddress());		
		return true;
	}

	protected Boolean compareHome(HomeDTO h1, HomeDTO h2) {
		assertEquals(h1.getHomeId(), h2.getHomeId());
		assertEquals(h1.getType(), h2.getType());		
		compareAddress(h1.getAddress(), h2.getAddress());		
		return true;
	}
	
	protected Boolean comparePerson(PersonDTO dto, Person p) {		
		assertEquals(dto.getPersonId(), p.getId());
		assertEquals(dto.getFirstName(), p.getFirstName());
		assertEquals(dto.getLastName(), p.getLastName());
		assertEquals(dto.getAge(), p.getAge());
		compareHome(dto.getHome(), p.getHome());
		return true;
	}
	
	protected Boolean comparePerson(Person p1, Person p2) {		
		assertEquals(p1.getId(), p2.getId());
		assertEquals(p1.getFirstName(), p2.getFirstName());
		assertEquals(p1.getLastName(), p2.getLastName());
		assertEquals(p1.getAge(), p2.getAge());
		compareHome(p1.getHome(), p2.getHome());
		return true;
	}
	
	protected Boolean comparePerson(PersonDTO dto1, PersonDTO dto2) {		
		assertEquals(dto1.getPersonId(), dto2.getPersonId());
		assertEquals(dto1.getFirstName(), dto2.getFirstName());
		assertEquals(dto1.getLastName(), dto2.getLastName());
		assertEquals(dto1.getAge(), dto2.getAge());
		compareHome(dto1.getHome(), dto2.getHome());
		return true;
	}
}
