package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

//DAO é o padrão de projetos para persistência de dados
//para cada classe de modelo existe uma classe DAO
//que é reponsável por conter os métodos de acesso ao BD
public class CategoriaDAO {
	private EntityManager em;

	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		//cadastrando produto no banco de dados usando a JPA
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		//garante que a categoria está no estado managed
		this.em.merge(categoria);
	}
	
	public void remove(Categoria categoria) {
		//a entidade precisa estar no estado managed para ser excluída
		categoria = this.em.merge(categoria);
		this.em.remove(categoria);
	}
	
	
}
