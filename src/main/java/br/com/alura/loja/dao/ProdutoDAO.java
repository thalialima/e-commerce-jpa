package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

//DAO é o padrão de projetos para persistência de dados
//para cada classe de modelo existe uma classe DAO
//que é reponsável por conter os métodos de acesso ao BD como caomandos SQL e JPQL
public class ProdutoDAO {
	private EntityManager em;

	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto poduto) {
		//cadastrando produto no banco de dados usando a JPA
		this.em.persist(poduto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id); //método que busca uma única entidade pelo id
	}
	
	public List<Produto> buscarTodos(){
		//utiliza-se a linguagem JPQL(SQL OO)
		//a consulta é feita com o nome da entidade e utiliza-se um alias
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome){
		//utiliza-se a linguagem JPQL(SQL OO)
		//a consulta é feita com o nome da entidade e utiliza-se um alias
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nome){
		//utiliza-se a linguagem JPQL(SQL OO)
		//a consulta é feita com o nome da entidade e utiliza-se um alias
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoDoProdutoPeloNome(String nome){
		//utiliza-se a linguagem JPQL(SQL OO)
		//a consulta é feita com o nome da entidade e utiliza-se um alias
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
}
