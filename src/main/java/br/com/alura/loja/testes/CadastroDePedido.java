package br.com.alura.loja.testes;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		Produto produto = produtoDAO.buscarPorId(1l);
		Produto produto2 = produtoDAO.buscarPorId(2l);
		Produto produto3 = produtoDAO.buscarPorId(3l);
	
		Cliente cliente = clienteDAO.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente);
		pedido.addItem(new ItemPedido(10, pedido, produto));
		pedido.addItem(new ItemPedido(40, pedido, produto2));

		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.addItem(new ItemPedido(2, pedido2, produto3));
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		pedidoDAO.cadastrar(pedido);
		pedidoDAO.cadastrar(pedido2);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
		System.out.println("VALOR TOTAL VENDIDO: " + totalVendido);
		
		//arrumar isso
		List<RelatorioDeVendasVo> relatorio = pedidoDAO.relatorioDeVendas();
		relatorio.forEach(System.out::println);
	}

	private static void popularBancoDeDados() {
		// instanciando uma entidade (transient)
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi", "Celular top", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Jogue até rachar", new BigDecimal("5000"), videogames);
		Produto notebook = new Produto("Notebook", "Notebook com SO Línux", new BigDecimal("1500"), informatica);

		Cliente cliente = new Cliente("Thalia", "010.010.010-11");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
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
		
		clienteDAO.cadastrar(cliente);

		// faz o commit no bd (BD)
		em.getTransaction().commit();

		// fecha o recurso (detached)
		em.close();
	}

}
