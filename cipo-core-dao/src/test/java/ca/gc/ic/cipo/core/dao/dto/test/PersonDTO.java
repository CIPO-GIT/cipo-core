package ca.gc.ic.cipo.core.dao.dto.test;

import ca.gc.ic.cipo.core.dao.dto.DTO;

public class PersonDTO implements DTO {

	private static final long serialVersionUID = 1L;
	private static Long idCount = 1L;
	
	private Long personId; 
	private String firstName;
	private String lastName;
	private Integer age;
	private HomeDTO home;

	public PersonDTO() {
		super();		
	}

	public PersonDTO(String firstName, String lastName, Integer age, HomeDTO home) {
		super();
		this.personId = idCount++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.home = home;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	public HomeDTO getHome() {
		return home;
	}

	public void setHome(HomeDTO home) {
		this.home = home;
	}

	@Override
	public String toString() {
		return "Id=" + personId + " Name=" + firstName + " " + lastName	+ " Age=" + age;  
	}
	
	
}
