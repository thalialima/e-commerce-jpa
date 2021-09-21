package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class PedidoDAO {

	private EntityManager em;

	public PedidoDAO(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		// cadastrando pedido no banco de dados usando a JPA
		this.em.persist(pedido);
	}

	public void atualizar(Pedido pedido) {
		// garante que o pedido está no estado managed
		this.em.merge(pedido);
	}

	public void remove(Pedido pedido) {
		// a entidade precisa estar no estado managed para ser excluída
		pedido = this.em.merge(pedido);
		this.em.remove(pedido);
	}

	public Pedido buscarPorId(Long id) {
		// método que busca uma única entidade pelo id
		return em.find(Pedido.class, id);
	}

	public List<Pedido> buscarTodos() {
		// utiliza-se a linguagem JPQL(SQL OO)
		// a consulta é feita com o nome da entidade e utiliza-se um alias
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Pedido.class).getResultList();
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	public List<RelatorioDeVendasVo> relatorioDeVendas() {
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo(" 
				+ "produto.nome, " + "SUM(item.quantidade), "
				+ "MAX(pedido.data)) " + "FROM Pedido pedido " 
				+ "JOIN pedido.itens item " + "JOIN item.produto produto " 
				+ "GROUP BY produto.nome " + "ORDER BY item.quantidade DESC ";
		return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
	}

}
