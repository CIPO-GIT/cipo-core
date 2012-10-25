package ca.gc.ic.cipo.core.dao.test;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.ic.cipo.core.dao.hibernate.HibernateDAO;
import ca.gc.ic.cipo.core.dao.model.test.Person;

/**
 * Unit test for a Simple DAO Test.
 */
@Transactional("hibernateTransactionManager")
@Service("GenericDAOTest") 
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration( locations = { "classpath:jUnit-applicationContext.xml" })
public class GenericDAOTest extends BaseTest {
	
	@Autowired
	private HibernateDAO dao;
	
	@Test
	public void testSave() {
		Serializable id = null;

		// Test save of bigbossA.
		id = dao.save(bigBossA.getHome().getAddress());
		assertEquals("Returned ID should match assigned ID", bigBossA.getHome().getAddress().getId(), id);

		id = dao.save(bigBossA.getHome());
		assertEquals("Returned ID should match assigned ID", bigBossA.getHome().getId(), id);
		
		id = dao.save(bigBossA);
		assertEquals("Returned ID should match assigned ID", bigBossA.getId(), id);

		// Test finding bigBossA.
		List<Person> list = dao.findAll(Person.class);
		assertEquals(1, list.size());
		assertEquals(bigBossA, list.get(0));
		assertEquals(bigBossA, dao.find(Person.class, bigBossA.getId()));

		dao.save(bossA.getHome().getAddress());
		dao.save(bossA.getHome());
		dao.save(bossA);
		
		// Verify if the relation Boss has been save.
		id = dao.save(joceA);
		assertEquals(bossA.getId(), joceA.getBoss().getId());
		
		// Verify if the relation BigBoss is ok.
		bigBossA.setFirstName("Johanne");
		Person findPerson = dao.find(Person.class, joceA.getId());
		assertEquals("Johanne", findPerson.getBoss().getBoss().getFirstName());
		bigBossA.setFirstName("BigBoss");
	}

	@Test
	public void testUpdate() {
		
		initDB();
		Person carol = copy(bossA);
		carol.setFirstName("Carol");
		dao.update(carol);

		assertEquals("The change should be made.", "Carol", dao.find(Person.class, joceA.getId()).getBoss()
				.getFirstName());

		carol.setLastName("Desbiens");

		Person example = new Person();
		example.setId(joceA.getId());
		assertEquals("The change should be made.", "Denis", dao.findByExample(Person.class, example));

		Person otherCarol = copy(bossA);
		try {
			dao.update(otherCarol);
			fail("Should throw exception when there is a persistent instance with the same identifier.");
		} catch (Exception e) {
		}
	}

	@Test
	public void testPersist() {
		dao.persist(bigBossA.getHome().getAddress());
		dao.persist(bigBossA.getHome());
		dao.persist(bigBossA);

		List<Person> list = dao.findAll(Person.class);
		assertEquals(1, list.size());
		assertEquals(bigBossA, list.get(0));

		assertEquals(bigBossA, dao.load(Person.class, bigBossA.getId()));

		dao.persist(bossA.getHome().getAddress());
		dao.persist(bossA.getHome());
		dao.persist(bigBossA);
		dao.persist(bossA);
		dao.persist(joceA);

		bigBossA.setFirstName("Johanne");
		assertEquals("Johanne", dao.load(Person.class, joceA.getId()).getBoss().getBoss().getFirstName());
		bigBossA.setFirstName("BigBoss");
	}

	@Test
	public void testMerge() {
		initDB();
		Person carol = copy(bossA);
		carol.setFirstName("Carol");
		Person attachedCarol = dao.merge(carol);

		assertEquals("The change should be made.", "Carol", dao.find(Person.class, joceA.getId()).getBoss()
				.getFirstName());

		Person example = new Person();
		example.setId(joceA.getId());
		
		carol.setLastName("Desbiens2");
		assertEquals("The change should not be made.", "Desbiens", dao.findByExample(Person.class, example));

		attachedCarol.setLastName("Desbiens2");
		assertEquals("The change should be made.", "Desbiens", dao.findByExample(Person.class, example));
	}

	@Test
	public void testDelete() {
		initDB();
	
		List<Person> list = dao.findAll(Person.class);
		int sizeBefore = list.size();

		assertTrue("Should return true when successfully deleting", dao.deleteById(Person.class, joceA.getId()));
		assertTrue("Should return true when successfully deleting", dao.delete(fredA));

		list = dao.findAll(Person.class);
		assertEquals(sizeBefore - 2, list.size());
		for (Person person : list) {
			if (person.getId().equals(joceA.getId()) || person.getId().equals(fredA.getId()))
				fail("Neither Joce nor Fred should now be in the DB");
		}

		dao.save(joceA);
		dao.save(fredA);

		list = dao.findAll(Person.class);
		assertEquals(sizeBefore, list.size());
		boolean joceFound = false, fredFound = false;
		for (Person person : list) {
			if (person.getFirstName().equals("Joce") && person.getLastName().equals("Denis"))
				joceFound = true;
			if (person.getFirstName().equals("Fred") && person.getLastName().equals("Pierre"))
				fredFound = true;
		}
		assertTrue("Joce and Fred should now be back in the DB", joceFound && fredFound);

		// Test deleting by non-existent ID.		
		Long unusedId = 99999999L;

		// deleteById should not throw an error
		assertFalse(dao.deleteById(Person.class, unusedId));

		Person fake = new Person();
		assertFalse("return false when no ID", dao.delete(fake));
		fake.setId(unusedId);
		assertFalse("return false when ID not found", dao.delete(fake));
	}

	@Test
	public void testLoad() {
		initDB();

		Person joce = new Person();
		dao.load(joce.getClass(), joceA.getId());
		assertEquals(joce.getId(), joceA.getId());
		assertEquals(joce.getAge(), joceA.getAge());
	}

	@Test
	public void testExample() {
		initDB();

		Person example = new Person();
		example.setFirstName("Joce");
		example.setLastName("Denis");

		assertEquals(joceA, dao.findByExample(Person.class, example));

		example.setAge(0);
		assertEquals(null, dao.findByExample(Person.class, example));
	}
    
}
