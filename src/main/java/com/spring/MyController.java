package com.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;





@RestController(value = "http://localhost:8088")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class MyController {

	@Autowired
	private MyRepository repository;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<Employee>> findAll() {
		return new ResponseEntity<List<Employee>>(repository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> save(@RequestBody Employee employee) {
		
		
		ResponseEntity<Employee> reponse = new ResponseEntity<Employee>(repository.save(employee),HttpStatus.CREATED);
		
		return reponse;
		}
	
	@GetMapping(value="/delete/{empId}")
	public ResponseEntity<List<Employee>> deleteEmployee(@PathVariable String empId) {
		repository.deleteById(Integer.parseInt(empId));
		return new ResponseEntity<List<Employee>>(repository.findAll(), HttpStatus.OK);
	}
}
