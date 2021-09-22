package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {
		popularBancoDeDados();

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		List<Produto> produtos = produtoDAO.bucarPorParametros("PS5", null, LocalDate.now());
		produtos.forEach(p -> System.out.println(p.getNome()));
		
	}

	private static void popularBancoDeDados() {
		// instanciando uma entidade (transient)
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiaomi", "Celular top", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Jogue até rachar", new BigDecimal("5000"), videogames);
		Produto notebook = new Produto("Notebook", "Notebook com SO Línux", new BigDecimal("1500"), informatica);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		// inicia a transação
		em.getTransaction().begin();

		// (managed)
		// em.persist(celulares);

		categoriaDAO.cadastrar(celulares);
		categoriaDAO.cadastrar(videogames);
		categoriaDAO.cadastrar(informatica);

		produtoDAO.cadastrar(celular);
		produtoDAO.cadastrar(videogame);
		produtoDAO.cadastrar(notebook);

		// faz o commit no bd (BD)
		em.getTransaction().commit();

		// fecha o recurso (detached)
		em.close();
	}
}
