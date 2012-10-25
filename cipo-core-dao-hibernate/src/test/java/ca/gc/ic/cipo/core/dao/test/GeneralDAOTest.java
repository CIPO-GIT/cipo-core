package ca.gc.ic.cipo.core.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.ic.cipo.core.dao.PersonDAO;
import ca.gc.ic.cipo.core.dao.model.test.Person;

/**
 * Unit test for a Simple DAO Test.
 */
@Transactional("hibernateTransactionManager")
@Service("GeneralDAOTest") 
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration( locations = { "classpath:jUnit-applicationContext.xml" })
public class GeneralDAOTest extends BaseTest {

	@Autowired
	private PersonDAO personDAO;

	
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	/**
	 * Just quickly check that all the methods basically work. 
	 */
	@Test
	public void testDAO() {
		
		// Create Fred.
		Person fred = new Person();
		fred.setFirstName("Fred");
		fred.setLastName("Pierre");
		fred.setAge(35);
		setup(fred);
		Long fredId = personDAO.create(fred);
		assertEquals(fredId, fred.getId());
		
		// Create Carol.
		Person carol = new Person();
		carol.setFirstName("Carol");
		carol.setLastName("Desbiens");
		carol.setAge(55);
		setup(carol);
		Long carolId = personDAO.create(carol);
		assertEquals(carolId, carol.getId());
	
		// GetAll.
		assertListEqual(new Person[] { carol, fred }, personDAO.findAll());

		// Search By Example
		Person carolExample = new Person();
		carolExample.setId(carol.getId());
		carolExample.setFirstName("Carol");
		@SuppressWarnings("unchecked")
		List<Person> persons = (List<Person>) personDAO.findByExample(carolExample);		
		assertEquals(1, persons.size());
		assertEquals(carol.getFirstName(), persons.get(0).getFirstName());

		// Delete
		personDAO.delete(carol);
		assertEquals(null, personDAO.find(carol.getId()));

		personDAO.deleteById(fred.getId());
		assertEquals(null, personDAO.find(fred.getId()));

		// Set new Id's
		carol.setId(Person.nextId());
		carolId = personDAO.create(carol);
		assertEquals(carolId, carol.getId());
		
		fred.setId(Person.nextId());
		fredId = personDAO.create(fred);
		assertEquals(fredId, fred.getId());

		// Test update.
		carol.setFirstName("CarolTheBoss");
		personDAO.update(carol);
		assertEquals("CarolTheBoss", (personDAO.find(carol.getId())).getFirstName());
		
		fred.setAge(35);
		personDAO.update(fred);
		assertEquals(new Integer(35), (personDAO.find(fred.getId())).getAge());
		
		// Test Update with a relation.
		fred.setBoss(carol);
		personDAO.update(fred);
		assertEquals("CarolTheBoss", (personDAO.find(fred.getId())).getBoss().getFirstName());		
	}
}
