package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDAO {
	private EntityManager em;

	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto poduto) {
		//cadastrando pruduto no banco de dados usando a JPA
		this.em.persist(poduto);
	}
	
	
}
