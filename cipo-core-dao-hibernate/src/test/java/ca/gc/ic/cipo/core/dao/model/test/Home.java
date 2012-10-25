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

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ca.gc.ic.cipo.core.dao.model.PersistentBaseEntity;

public class Home extends PersistentBaseEntity<Long> {

	private static final long serialVersionUID = -821394706130954264L;
	private static Long nextId = 100L;
	
	private String type;
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(mappedBy = "home", fetch = FetchType.EAGER)
	private List<Person> residents;

	public Home() {
		
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Person> getResidents() {
		return residents;
	}

	public void setResidents(List<Person> residents) {
		this.residents = residents;
	}

	public String toString() {
		return "Home::id:" + getId() + ",type:" + type + ",address:" + address;
	}
	
	public static Long nextId() {
		return nextId++;
	}	
}
