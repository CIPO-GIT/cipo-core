/* Copyright 2009 The Revere Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.gc.ic.cipo.core.dao.test;

import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;

import ca.gc.ic.cipo.core.dao.model.test.Address;
import ca.gc.ic.cipo.core.dao.model.test.Home;
import ca.gc.ic.cipo.core.dao.model.test.Person;

import junit.framework.Assert;
import org.junit.Before;
import junit.framework.TestCase;

@ContextConfiguration( locations = { "classpath:jUnit-applicationContext.xml" })
public abstract class BaseTest extends TestCase {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// The Java Team :-)
	@Autowired
	protected Person joceA;
	
	@Autowired
	protected Person pierreA;
	
	@Autowired
	protected Person fredA;
	
	@Autowired
	protected Person declanB;
	
	// The immediate Boss / Manager.
	@Autowired
	protected Person bossA;
	
	@Autowired
	protected Person bossB;
	
	// And the big bosses.
	@Autowired
	protected Person bigBossA;
	
	@Autowired
	protected Person bigBossB;
	
	@Override
	@Before
	public void setUp() throws Exception {
		reset();
		super.setUp();
	}
	
	protected void reset() {
		
		for (Person p : new Person[] { joceA, pierreA, fredA, declanB, bossA, bossB, bigBossA, bigBossB }) {
			p.setId(null);
			p.getHome().setId(null);
			p.getHome().getAddress().setId(null);
			setup(p);
		}
	}

	public void initDB() {
		insert(bossA.getHome().getAddress());
		insert(bossA.getHome());
		
		insert(bossB.getHome().getAddress());
		insert(bossB.getHome());
		
		insert(bigBossA.getHome().getAddress());
		insert(bigBossA.getHome());

		for (Person p : new Person[] { joceA, pierreA, fredA, declanB, bossA, bossB, bigBossA, bigBossB }) {
			insert(p);
		}
	}

	private Number insert(String sql, Class<?>[] types, Object... args) {
		int[] stypes = new int[types.length];
		for (int i = 0; i < types.length; i++) {
			Class<?> type = types[i];
			if (type.equals(Long.class)) {
				stypes[i] = Types.INTEGER;
			} else if (type.equals(Integer.class)) {
				stypes[i] = Types.INTEGER;
			} else if (type.equals(String.class)) {
				stypes[i] = Types.VARCHAR;
			} else if (type.equals(Date.class)) {
				stypes[i] = Types.DATE;
			} else if (type.equals(Float.class)) {
				stypes[i] = Types.FLOAT;
			} else if (type.equals(Double.class)) {
				stypes[i] = Types.DOUBLE;
			} else if (type.equals(Boolean.class)) {
				stypes[i] = Types.BOOLEAN;
			}
		}
		
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(sql, stypes);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.getJdbcOperations().update(factory.newPreparedStatementCreator(args), keyHolder);
		return keyHolder.getKey();
	}

	protected void insert(Person p) {
		String sql = "INSERT INTO person (age, dob, first_name, last_name, isMale, boss_id, home_id) values (?1, ?2, ?3, ?4, ?5, ?6, ?7)";
		Class<?>[] types = new Class<?>[] { Integer.class, Date.class, String.class, String.class, 
				Boolean.class, Long.class, Long.class };
		Number id = insert(sql, types, p.getAge(), p.getDob(), p.getFirstName(), p.getLastName(), p.getIsMale(), 
				p.getBoss() != null ? p.getBoss().getId() : null, p.getHome().getId());
		p.setId(id.longValue());
	}

	protected void insert(Home h) {
		String sql = "INSERT INTO home (type, address_id) values (?1, ?2)";
		Class<?>[] types = new Class<?>[] { String.class, Long.class };
		Number id = insert(sql, types, h.getType(), h.getAddress().getId());
		h.setId(id.longValue());
	}

	protected void insert(Address a) {
		String sql = "INSERT INTO address (city, state, street, zip) values (?1, ?2, ?3, ?4)";
		Class<?>[] types = new Class<?>[] { String.class, String.class, String.class, String.class };
		Number id = insert(sql, types, a.getCity(), a.getState(), a.getStreet(), a.getZip());
		a.setId(id.longValue());
	}

	protected Person setup(Person p) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.YEAR, -p.getAge());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		p.setDob(cal.getTime());

		return p;
	}

	protected Person copy(Person p) {
		Person cpy = new Person();
		cpy.setId(p.getId());
		cpy.setBoss(p.getBoss());
		cpy.setFirstName(p.getFirstName());
		cpy.setLastName(p.getLastName());
		cpy.setAge(p.getAge());
		cpy.setDob(p.getDob());
		cpy.setIsMale(p.getIsMale());
		return cpy;
	}

	protected Home copy(Home h) {
		Home cpy = new Home();
		cpy.setId(h.getId());
		cpy.setType(h.getType());
		return cpy;
	}

	protected Address copy(Address a) {
		Address cpy = new Address();
		cpy.setId(a.getId());
		cpy.setStreet(a.getStreet());
		cpy.setCity(a.getCity());
		cpy.setState(a.getState());
		cpy.setZip(a.getZip());
		return cpy;
	}

	protected void assertListEqual(Person[] expected, List<Person> actual) {
		Assert.assertEquals("The list did not have the expected length", expected.length, actual.size());

		HashMap<Long, Object> unmatched = new HashMap<Long, Object>();
		for (Person person : expected) {
			unmatched.put(person.getId(), "");
		}
		for (Person person : actual) {
			unmatched.remove(person.getId());
		}

		if (unmatched.size() != 0)
			Assert.fail("The list did not match the expected results.");
	}

	protected void assertListEqual(List<?> actual, Object... expected) {
		Assert.assertEquals("The list did not have the expected length", expected.length, actual.size());

		List<Object> remaining = new LinkedList<Object>();
		remaining.addAll(actual);

		for (Object o : expected) {
			if (!remaining.remove(o))
				Assert.fail("The list did not match the expected results.");
		}
	}

	protected void assertArrayEqual(Object[] actual, Object... expected) {
		Assert.assertEquals("The array did not have the expected length", expected.length, actual.length);

		List<Object> remaining = new LinkedList<Object>();
		for (Object o : actual) {
			remaining.add(o);
		}

		for (Object o : expected) {
			if (!remaining.remove(o))
				Assert.fail("The array did not match the expected results.");
		}
	}

	protected void assertListOrderEqual(Person[] expected, List<Person> actual) {
		Assert.assertEquals("The list did not have the expected length", expected.length, actual.size());

		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].getId().equals(actual.get(i).getId()))
				Assert.fail("The list did not match the expected results.");
		}
	}

}
