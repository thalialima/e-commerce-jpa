package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		//instanciando uma entidade (transient)
		Categoria celulares = new Categoria("CELULARES");

		EntityManager em = JPAUtil.getEntityManager();

		// inicia a transação
		em.getTransaction().begin();

		//(managed)
		em.persist(celulares);
		
		celulares.setNome("XPTO");
		
		em.flush();
		em.clear();

		// faz o commit no bd (BD)
		em.getTransaction().commit();

		// fecha o recurso  (detached)
		em.close();
	
	}
}
