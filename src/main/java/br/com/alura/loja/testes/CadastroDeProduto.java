package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("LG Q6", "Celular top", new BigDecimal("800"), celulares);

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);

		// inicia a transação
		em.getTransaction().begin();

		// guarda os dados
		categoriaDAO.cadastrar(celulares);
		produtoDAO.cadastrar(celular);

		// faz o commit no bd
		em.getTransaction().commit();

		// fecha o recurso
		em.close();
	}
}
