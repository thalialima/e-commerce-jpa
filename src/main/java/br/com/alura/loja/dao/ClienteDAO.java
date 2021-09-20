package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Pedido;

public class ClienteDAO {

	private EntityManager em;

	public ClienteDAO(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Cliente cliente) {
		// cadastrando pedido no banco de dados usando a JPA
		this.em.persist(cliente);
	}
	
	public void atualizar(Cliente cliente) {
		//garante que o pedido está no estado managed
		this.em.merge(cliente);
	}
	
	public void remove(Cliente cliente) {
		//a entidade precisa estar no estado managed para ser excluída
		cliente = this.em.merge(cliente);
		this.em.remove(cliente);
	}
	

	public Cliente buscarPorId(Long id) {
		// método que busca uma única entidade pelo id
		return em.find(Cliente.class, id);
	}

	public List<Cliente> buscarTodos() {
		// utiliza-se a linguagem JPQL(SQL OO)
		// a consulta é feita com o nome da entidade e utiliza-se um alias
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Cliente.class).getResultList();
	}
	
	
	
	
	
}
