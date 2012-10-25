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

public class AddressDTO implements DTO {
	
	private static final long serialVersionUID = 1L;
	private static Long idCount = 1L;
	
	private Long addressId;
	private String street;
	private String city;
	private String state;
	private String zip;

	public AddressDTO() {
		super();
	}
	
	public AddressDTO(String street, String city, String state, String zip) {
		super();
		this.addressId = idCount++;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return "Id=" + addressId + " Street=" + street + " City=" + city + " State=" + state + " Zip=" + zip;
	}
}
