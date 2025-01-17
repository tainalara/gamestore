package com.generation.gamestore.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity  						// indicar que a classe é uma tabela
@Table (name = "tb_produtos") // indica o nome da tabela no bd (banco de dados)
public class Produtos {
	
	@Id //Identifica que esta é a chave primária da tabela tb_produtos
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "O campo nome é obrigatório para o cadastro") //Para informar que o campo não pode ficar vazio
	@Size (min = 3, max = 20, message = "O Nome do produto deve ter entre 3 e 20 caracteres") //Limitar o número de caracteres do campo
	private String nome;
	
	@Size (min = 5, max = 100, message = "A Descrição produto deve ter entre 5 e 100 caracteres") //Limitar o número de caracteres do campo
	private String descricao_produto;
	
	@NotNull (message = "O campo Preço é obrigatório para o cadastro") 
	@DecimalMin (value = "0.0", inclusive = false, message = "O preço cadastrado deve ser maior do que Zero")
	@Column (nullable = false, precision = 10, scale = 2) // Define que esse campo não ficar null, que pode receber até 10 números e 2 casas depois da vírgula
	private BigDecimal preco;
	
	@NotNull (message = "O campo Quantidade no Estoque é obrigatório para o cadastro") 
	@Min (value = 0, message = "A Quantidade no Estoque não pode ser negativa")
	private Long quantidade;
	
	@ManyToOne //Mostra a Relação de Muitas para Uma
	@JsonIgnoreProperties("produtos") //Vai criar um sub Objeto do Objeto Produtos
	private Categorias categorias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao_produto() {
		return descricao_produto;
	}

	public void setDescricao_produto(String descricao_produto) {
		this.descricao_produto = descricao_produto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

}
