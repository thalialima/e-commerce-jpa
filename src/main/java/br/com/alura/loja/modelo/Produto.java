package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

//usa-se a importação da especificação, para não ficar preso às implementações
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//---------Mapeamento de Entidade---------------------

//anotação da JPA que indica q essa classe está sendo mapeada para uma tabela no BD
@Entity

//indica o nome da tabela no BD
@Table(name = "produtos")

//dá um nome para a query
//coloca a definição da consulta na entidade
@NamedQuery(name = "Produto.produtosPorCategoria", 
query = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome")
public class Produto {

	// os nomes dos atributos precisam ser iguais aos atributos da tabela do BD
	// assim, os nomes são associados automaticamente
	// Caso contrário, será necessário utilizar a anotação
	// @Column(name="nomeDaTable")

	// indica o id para a JPA
	@Id
	// Indica que o atributo é gerado automaticamente pelo BD
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descicao;
	private BigDecimal preco;
	// preenche o atributo com a data atual
	private LocalDate dataCadastro = LocalDate.now();
	
	//Indica a cardinalidade da relação
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}
	
	public Produto(String nome, String descicao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descicao = descicao;
		this.preco = preco;
		this.categoria = categoria;
	}

	// getters and setters
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

	public String getDescicao() {
		return descicao;
	}

	public void setDescicao(String descicao) {
		this.descicao = descicao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
