package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	// a factory deve ser criada uma única vez na aplicação
	// Factory que cria o EntityManager
	// e a Persistence cria a factory
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja");

	public static EntityManager getEntityManager() {
		// interface que faz a ponte entre o Java e o BD
		// é o gerente das entidades
		return FACTORY.createEntityManager();
	}
}
