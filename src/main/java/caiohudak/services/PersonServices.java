package caiohudak.services;
import static caiohudak.mapper.ObjectMapper.parseListObjects;
import static caiohudak.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caiohudak.controllers.PersonController;
import caiohudak.data.dto.v1.PersonDTO;
import caiohudak.exception.RequiredObjectIsNullException;
import caiohudak.exception.ResourceNotFoundException;
import caiohudak.mapper.custom.PersonMapper;
import caiohudak.model.Person;
import caiohudak.repository.PersonRepository;

@Service
public class PersonServices {

	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper converter;
	
	private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());
	
	public List<PersonDTO> findAll() {
		logger.info("Finding all People!");
		var people = parseListObjects(repository.findAll(), PersonDTO.class);
		people.forEach(p -> addHateoasLinks(p));
		return people;
	}
	
	public PersonDTO findById(Long id) {
		logger.info("Finding one Person!");
		
		var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
		
		var dto= parseObject(entity, PersonDTO.class);
		addHateoasLinks(dto);
		return dto;
	}

	public PersonDTO create(PersonDTO person) {
		
		if(person==null) throw new RequiredObjectIsNullException();
		logger.info("Creating one Person!");
		var entity = parseObject(person, Person.class);
		var dto = parseObject(repository.save(entity), PersonDTO.class);
		addHateoasLinks(dto);
		return dto;
	}
	
	
	public PersonDTO update(PersonDTO person) {
		
		if(person==null) throw new RequiredObjectIsNullException();
		logger.info("Updating one Person!");
		Person entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
	    var dto = parseObject(repository.save(entity), PersonDTO.class);
	    addHateoasLinks(dto);
		return dto;
		
	}

	
	public void delete(Long id) {
		logger.info("Deleting one Person!");
		
		Person entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entity);
		
	}

	private void addHateoasLinks (PersonDTO dto) {
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
		dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
		dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
		dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
		dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
	}
}
