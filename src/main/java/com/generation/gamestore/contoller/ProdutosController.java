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

import com.generation.gamestore.model.Produtos;
import com.generation.gamestore.repository.ProdutosRepository;

import jakarta.validation.Valid;

@RestController // Mostra ao Spring que essa Classe é uma Controller
@RequestMapping("/produtos") // Define qual endpoint desta Classe
@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite consumir a API
public class ProdutosController {

	@Autowired // Injeção de Dependência
	private ProdutosRepository produtosRepository;

	@PostMapping // Método para cadastrar Produtos
	public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produtos) {
		// Salva a postagem no banco e retorna status 201 (Created)
		return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));
	}

	@GetMapping // Listar todas os Produtos Cadastradas
	public ResponseEntity<List<Produtos>> getAll() {
		return ResponseEntity.ok(produtosRepository.findAll());
	}

	@GetMapping("/{id}") // Mostrar Categoria por Id específica
	public ResponseEntity<Produtos> getById(@PathVariable Long id) {
		return produtosRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nome/{nome}") // Mostrar pesquisa por nome
	public ResponseEntity<List<Produtos>> getByTipo(@PathVariable String nome) {
		return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produtos> put(@PathVariable Long id, @Valid @RequestBody Produtos produtos) {
		return produtosRepository.findById(id).map(resposta -> {
			// Garante que o ID do corpo seja consistente com o ID da URL
			produtos.setId(id);
			return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produtos));
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produtos> produto = produtosRepository.findById(id);

		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		produtosRepository.deleteById(id);
	}

}
