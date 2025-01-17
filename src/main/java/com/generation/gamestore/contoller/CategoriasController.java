package com.generation.gamestore.contoller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.gamestore.model.Categorias;
import com.generation.gamestore.repository.CategoriasRepository;

import jakarta.validation.Valid;

@RestController //Mostra ao Spring que essa Classe é uma Controller
@RequestMapping("/categorias")//Define qual endpoint desta Classe
@CrossOrigin (origins = "*", allowedHeaders = "*") //Permite consumir a API 
public class CategoriasController {
	
	@Autowired //Injeção de Dependência
	private CategoriasRepository categoriasRepository;
	
	@PostMapping //Método para cadastrar Categorias
	public ResponseEntity<Categorias> post(@Valid @RequestBody Categorias categorias) {
		// Salva a postagem no banco e retorna status 201 (Created)
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categorias));
	}
	
	@GetMapping // Listar todas as Categorias Cadastradas
	public ResponseEntity<List<Categorias>> getAll(){
		return ResponseEntity.ok(categoriasRepository.findAll());
	}
	
	@GetMapping("/{id}") //Mostrar Categoria por Id específica
	public ResponseEntity<Categorias> getById(@PathVariable Long id){
	    return categoriasRepository.findById(id)
	            .map(resposta -> ResponseEntity.ok(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	 }
	 
	@GetMapping("tipo/{tipo}") //Mostrar pesquisa por tipo de categoria (nome)
	public ResponseEntity<List<Categorias>> getByTipo(@PathVariable String tipo) {
		return ResponseEntity.ok(categoriasRepository.findAllByTipoContainingIgnoreCase(tipo));
		}
	 
	@PutMapping
	public ResponseEntity<Categorias> put(@Valid @RequestBody Categorias categorias)  {
		return categoriasRepository.findById(categorias.getId())
				.map( resposta -> ResponseEntity.status(HttpStatus.OK)
					.body (categoriasRepository.save(categorias)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Categorias> categorias = categoriasRepository.findById(id);
		
		if(categorias.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		categoriasRepository.deleteById(id);
	}
	
	 
}
