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

public class Home extends PersistentBaseEntity<Long>{

	private static final long serialVersionUID = 1L;
	private static Long idCount = 1L;
	
	private String type;
	private Address address;
	
	public Home() {
		super();
	}
	
	public Home(String type, Address address) {
		super(idCount++);
		this.type = type;
		this.address = address;		
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

	@Override
	public String toString() {
		return "Id=" + getId() + " Type=" + type;
	}
}
