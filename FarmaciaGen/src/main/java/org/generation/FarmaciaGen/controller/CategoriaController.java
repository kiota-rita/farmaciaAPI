package org.generation.FarmaciaGen.controller;

import java.util.List;

import org.generation.FarmaciaGen.model.Categoria;
import org.generation.FarmaciaGen.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categoria") //end point
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping //get por tudo
	public ResponseEntity<List <Categoria>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") //get por id
	public ResponseEntity<Categoria> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());	
	}
	
	@GetMapping("/categoria{categoria}") //get por categoria
	public ResponseEntity<List<Categoria>> getByName(@PathVariable String categoria){
		return ResponseEntity.ok(repository.findAllByCategoriaContainingIgnoreCase(categoria));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> produto (@RequestBody Categoria tipo){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tipo));	
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put (@RequestBody Categoria tipo){
		return ResponseEntity.ok(repository.save(tipo));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id){
		repository.deleteById(id);
	}
}