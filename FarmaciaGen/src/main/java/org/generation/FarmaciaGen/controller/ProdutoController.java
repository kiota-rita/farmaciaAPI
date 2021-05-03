package org.generation.FarmaciaGen.controller;

import java.util.List;

import org.generation.FarmaciaGen.model.Produto;
import org.generation.FarmaciaGen.repository.ProdutoRepository;

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
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping //quando a api for consumida acessará esse metodo
	public ResponseEntity<List<Produto>> GetAll(){ //metodo
		return ResponseEntity.ok(repository.findAll()); //chama o findAll que esta na interface repository
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByTitulo(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping 
	public ResponseEntity<Produto> post(@RequestBody Produto produto){ //pega o que vem no corpo da requisição
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto)); //passa a confirmação de que a postagem foi criada e salva
	}
	
	@PutMapping //atualiza os campos do db
	public ResponseEntity<Produto> put(@RequestBody Produto produto){ //pega o que vem no corpo da requisição
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto)); //salva a postagem e diz que o status da requisição foi ok
	}
	
	@DeleteMapping("/{id}") //deleta a postagem pelo numero do id
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
