package com.generation.gamestore.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity  						// indicar que a classe é uma tabela
@Table (name = "tb_categorias") // indica o nome da tabela no bd (banco de dados)
public class Categorias {
	
	@Id //Identifica que esta é a chave primária da tabela tb_categorias
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "Você precisa preencher o nome do Tipo") //Para informar que o campo não pode ficar vazio
	@Size (min = 3, max = 20, message = "O nome do Tipo deve ter entre 3 e 100 caracteres") //Limitar o número de caracteres do campo
	private String tipo;
	
	@Size (min = 5, max = 100, message = "A Descrição produto deve ter entre 5 e 100 caracteres")
	private String descricao_tipo;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "categorias", cascade = CascadeType.REMOVE)// Mostra a Relação de Uma para Muitas
	@JsonIgnoreProperties("categorias")
	private List<Produtos> produtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao_tipo() {
		return descricao_tipo;
	}

	public void setDescricao_tipo(String descricao_tipo) {
		this.descricao_tipo = descricao_tipo;
	}

	public List<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}
	
	
	
}
