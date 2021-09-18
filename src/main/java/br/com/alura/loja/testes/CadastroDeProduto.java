package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		cadastrarProduto();
		
		//consultas pelo id
		Long id = 1l;
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		Produto p = produtoDAO.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p.getNome()));
		
		BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoPeloNome("Xiaomi");
		System.out.println("Preço do Produto: " + precoDoProduto);

	
	}

	private static void cadastrarProduto() {
		//instanciando uma entidade (transient)
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi", "Celular top", new BigDecimal("800"), celulares);
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);

		// inicia a transação
		em.getTransaction().begin();

		//(managed)
		//em.persist(celulares);
		
		categoriaDAO.cadastrar(celulares);
		produtoDAO.cadastrar(celular);
		

		// faz o commit no bd (BD)
		em.getTransaction().commit();

		// fecha o recurso  (detached)
		em.close();
	}
}
