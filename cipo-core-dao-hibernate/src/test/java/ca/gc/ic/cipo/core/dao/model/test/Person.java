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
package ca.gc.ic.cipo.core.dao.model.test;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ca.gc.ic.cipo.core.dao.model.PersistentBaseEntity;

public class Person extends PersistentBaseEntity<Long> {

	private static final long serialVersionUID = 6608434103834852730L;
	private static Long nextId = 100L;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@ManyToOne
	@JoinColumn(name="boss_id")
	private Person boss;
	private Integer age;
	private Date dob;
	private Boolean isMale;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="home_id")
	private Home home;
	
	public Person() {}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Person(String firstName, String lastName, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Person getBoss() {
		return boss;
	}

	public void setBoss(Person boss) {
		this.boss = boss;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	public Boolean getIsMale() {
		return isMale;
	}

	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Person))
			return false;
		Person p = (Person) o;

		if (!(firstName == null ? p.firstName == null : firstName
				.equals(p.firstName)))
			return false;
		if (!(lastName == null ? p.lastName == null : lastName
				.equals(p.lastName)))
			return false;

		return true;
	}

	public String toString() {
		return "Person::id:" + getId() + ",firstName:" + firstName + ",lastName:" + lastName + ",age:" + age + ",dob:" + dob;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + (firstName == null ? 0 : firstName.hashCode());
		hash = hash * 31 + (lastName == null ? 0 : lastName.hashCode());
		return hash;
	}

	public static Long nextId() {
		return nextId++;
	}		
}
