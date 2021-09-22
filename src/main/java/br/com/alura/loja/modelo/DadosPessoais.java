package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;

//indica que a clase é embutida em uma entidade
@Embeddable
public class DadosPessoais {
	
	private String nome;
	private String cpf;
	
	public DadosPessoais() {
		// TODO Auto-generated constructor stub
	}

	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getCpf() {
		return this.cpf;
	}

	public String getNome() {
		return this.nome;
	}


}
