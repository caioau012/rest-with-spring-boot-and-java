package caiohudak.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import caiohudak.controllers.docs.BookControllerDocs;
import caiohudak.data.dto.v1.BookDTO;
import caiohudak.services.BookServices;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/book")
@Tag(name = "Book", description = "Endpoints for Managing books")
public class BookController implements BookControllerDocs {
	
	@Autowired
	private BookServices service;
	
	@Override
	@GetMapping(value = "/{id}", produces =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
	public BookDTO findById(@PathVariable("id") Long id) {
		 return service.findById(id);
	}
	
	@Override
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
	public List<BookDTO> findAll() {
		return service.findAll();
	}
	
	@Override
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}, produces =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public BookDTO create(@RequestBody BookDTO book) {
		return service.create(book);
	}
	
	@Override
	@PutMapping(consumes =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}, produces =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
	
	public BookDTO update(@RequestBody BookDTO Book) {
		return service.update(Book);
	}
	
	@Override
	@DeleteMapping(value = "/{id}")

	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
