package br.com.jpa.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.jpa.loja.dao.CategoriaDao;
import br.com.jpa.loja.dao.ClienteDao;
import br.com.jpa.loja.dao.PedidoDao;
import br.com.jpa.loja.dao.ProdutoDao;
import br.com.jpa.loja.modelo.Categoria;
import br.com.jpa.loja.modelo.Cliente;
import br.com.jpa.loja.modelo.ItemPedido;
import br.com.jpa.loja.modelo.Pedido;
import br.com.jpa.loja.modelo.Produto;
import br.com.jpa.loja.util.JPAUtil;
import br.com.jpa.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
					
		popularBancoDeDados();
					
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(new BigDecimal("1"));
		em.getTransaction().begin();
		
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();
		
		
	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("Celulares");
		Produto celular = new Produto("Xiaomi Redmi", "BOM", new BigDecimal("800"), celulares);
		Produto ps5 = new Produto("Playstation", "5", new BigDecimal("15200"), celulares);
		
		
		Cliente cliente = new Cliente("Laura", "123456");
				
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		CategoriaDao Catdao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		Catdao.cadastrar(celulares);
		dao.cadastrar(celular);
		dao.cadastrar(ps5);
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}

}
