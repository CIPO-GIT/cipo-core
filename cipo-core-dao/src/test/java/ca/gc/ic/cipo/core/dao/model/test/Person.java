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

import ca.gc.ic.cipo.core.dao.model.PersistentBaseEntity;

public class Person extends PersistentBaseEntity<Long> {

	private static final long serialVersionUID = 6608434103834852730L;
	static Long idCount = 1L;

	private String firstName;
	private String lastName;
	private Integer age;
	private Home home;
	
	public Person() {
		super();
	}
	
	public Person(String firstName, String lastName, Integer age, Home home) {
		super(idCount++);
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.home = home;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}


	@Override
	public String toString() {
		return "Id=" + getId() + " Name=" + firstName + " " + lastName + " Age=" + age; 
	}
}
