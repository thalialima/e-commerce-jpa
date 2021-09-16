package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.modelo.Produto;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Produto celular = new Produto();
		celular.setNome("LG Q6");
		celular.setDescicao("Celular top");
		celular.setPreco(new BigDecimal("800"));
		
		//Factory que cria o EntityManager
		//e a Persistence cria a factory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");	
		
		//interface que faz a ponte entre o Java e o BD
		//é o gerente das entidades
		EntityManager em = factory.createEntityManager();
		
		//inicia a transação
		em.getTransaction().begin();
		
		//guarda os dados
		em.persist(celular);
		
		//faz o commit no bd
		em.getTransaction().commit();
		
		//fecha o recurso
		em.close();
	}
}
