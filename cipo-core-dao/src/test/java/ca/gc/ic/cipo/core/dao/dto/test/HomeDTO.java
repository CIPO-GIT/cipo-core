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
package ca.gc.ic.cipo.core.dao.dto.test;

import ca.gc.ic.cipo.core.dao.dto.DTO;

public class HomeDTO implements DTO {

	private static final long serialVersionUID = 1L;
	private static Long idCount = 1L;
	
	private Long homeId;
	private String type;
	private AddressDTO address;
	
	public HomeDTO() {
		super();
	}
	
	public HomeDTO(String type, AddressDTO address) {
		super();
		this.homeId = idCount++;
		this.type = type;
		this.address = address;
	}

	public Long getHomeId() {
		return homeId;
	}

	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Id=" + homeId + " Type=" + type;
	}
}
