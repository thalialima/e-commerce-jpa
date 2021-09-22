package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		// cadastrando produto no banco de dados usando a JPA
		this.em.persist(poduto);
	}

	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id); // método que busca uma única entidade pelo id
	}

	public List<Produto> buscarTodos() {
		// utiliza-se a linguagem JPQL(SQL OO)
		// a consulta é feita com o nome da entidade e utiliza-se um alias
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		// utiliza-se a linguagem JPQL(SQL OO)
		// a consulta é feita com o nome da entidade e utiliza-se um alias
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		// utiliza-se a linguagem JPQL(SQL OO)
		// a consulta é feita com o nome da entidade e utiliza-se um alias
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class).setParameter("nome", nome)
				.getResultList();
	}

	public BigDecimal buscarPrecoDoProdutoPeloNome(String nome) {
		// utiliza-se a linguagem JPQL(SQL OO)
		// a consulta é feita com o nome da entidade e utiliza-se um alias
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
	}

	// os parâmetros desse método são opcionais
	public List<Produto> bucarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
		// verifica se o nome é nulo e vazio
		if (nome != null && !nome.trim().isEmpty()) {
			jpql += " AND p.nome = :nome ";
		}
		if (preco != null) {
			jpql += " AND p.preco = :preco ";
		}
		if (dataCadastro != null) {
			jpql += " AND p.dataCadastro = :dataCadastro ";
		}

		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", nome);
		}
		if (preco != null) {
			query.setParameter("preco", preco);
		}
		if (dataCadastro != null) {
			query.setParameter("dataCadastro", dataCadastro);
		}

		return query.getResultList();

	}

	// os parâmetros desse método são opcionais
	public List<Produto> bucarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		
		if (nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}
		if (preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		if (dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}
		
		query.where(filtros);
		
		return em.createQuery(query).getResultList();

	}

}







