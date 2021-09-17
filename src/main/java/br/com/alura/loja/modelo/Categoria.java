package br.com.alura.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//anotação da JPA que indica q essa classe está sendo mapeada para uma tabela no BD
@Entity

//indica o nome da tabela no BD
@Table(name = "categorias")
public class Categoria {

	// indica o id para a JPA
	@Id
	// Indica que o atributo é gerado automaticamente pelo BD
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

}
