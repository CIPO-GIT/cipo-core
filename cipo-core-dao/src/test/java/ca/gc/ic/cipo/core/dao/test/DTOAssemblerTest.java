package ca.gc.ic.cipo.core.dao.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.gc.ic.cipo.core.dao.dto.dozer.DTOAssembler;
import ca.gc.ic.cipo.core.dao.dto.test.PersonDTO;
import ca.gc.ic.cipo.core.dao.model.test.Person;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration( locations = { "classpath:jUnit-applicationContext.xml" })
public class DTOAssemblerTest extends DTOBaseTest {

	@Autowired
	private DTOAssembler assembler;	
	
	@Test
	public void testToDto() {
		
		setupObjects();
		
		PersonDTO dto1 = assembler.toDto(PersonDTO.class, p1);
		assertTrue(comparePerson(dto1, p1));
		
		PersonDTO dto2 = assembler.toDto(PersonDTO.class, p2);
		assertTrue(comparePerson(dto2, p2));
		
		Person pp1 = assembler.toModel(Person.class, dto1);
		assertTrue(comparePerson(dto1, pp1));
			
		Person pp2 = assembler.toModel(Person.class, dto2);
		assertTrue(comparePerson(dto2, pp2));
				
		Person pp3 = assembler.toModel(pp2, dto1);
		assertTrue(comparePerson(pp3, pp2));
	}
	
	
	@Test
	public void testToDtos(){
		setupObjects();

		List<Person> persons = new ArrayList<Person>();
		persons.add(p1);
		persons.add(p2);
		
		// Convert the list to DTO's and compare their results.
		List<PersonDTO> dtos = assembler.toDtos(PersonDTO.class, persons);
		for (int i=0; i < dtos.size(); i++) {
			assertTrue(comparePerson(dtos.get(i), persons.get(i)));
		}

		// Convert the list of DTO's to their original states and compare their results.
		List<Person> personList = assembler.toModels(Person.class, dtos);
		for (int i=0; i < personList.size(); i++) {
			assertTrue(comparePerson(dtos.get(i), personList.get(i)));
		}
	}

	@Test
	public void testToModel() {
		setupObjects();
		
		Person pp1 = assembler.toModel(Person.class, pdto1);
		assertTrue(comparePerson(pdto1, p1));
		
		Person pp2 = assembler.toModel(Person.class, pdto2);
		assertTrue(comparePerson(pdto2, p2));
		
		PersonDTO dto1 = assembler.toDto(PersonDTO.class, pp1);
		assertTrue(comparePerson(dto1, p1));
		
		PersonDTO dto2 = assembler.toDto(PersonDTO.class, pp2);
		assertTrue(comparePerson(dto2, p2));
				
		PersonDTO dto3 = assembler.toDto(dto1, pp1);
		assertTrue(comparePerson(dto3, dto1));		
	}

	@Test
	public void testToModels() {
		setupObjects();
		
		List<PersonDTO> dtos = new ArrayList<PersonDTO>();
		dtos.add(pdto1);
		dtos.add(pdto2);
		
		List<Person> models = assembler.toModels(Person.class, dtos);
		for (int i=0; i < dtos.size(); i++) {
			assertTrue(comparePerson(dtos.get(i), models.get(i)));
		}
		
		List<PersonDTO> dtosList = assembler.toDtos(PersonDTO.class, models);
		for (int i=0; i < dtosList.size(); i++) {
			assertTrue(comparePerson(dtosList.get(i), models.get(i)));
		}
	}		
}
