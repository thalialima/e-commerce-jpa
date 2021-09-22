package br.com.alura.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

//anotação da JPA que indica q essa classe está sendo mapeada para uma tabela no BD
@Entity

//indica o nome da tabela no BD
@Table(name = "categorias")
public class Categoria {

	// indica o id para a JPA
	//@Id
	// Indica que o atributo é gerado automaticamente pelo BD
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@EmbeddedId
	private CategoriaId id;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "xpto");
	}

	public CategoriaId getId() {
		return id;
	}

	public void setId(CategoriaId id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.id.getNome();
	}


}
