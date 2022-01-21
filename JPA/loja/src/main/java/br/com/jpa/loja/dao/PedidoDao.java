package br.com.jpa.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.jpa.loja.modelo.Pedido;
import br.com.jpa.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {
	
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadastrar (Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public List<RelatorioDeVendasVo> relatorioDeVendas(){
		String jpql = "SELECT new br.com.jpa.loja.vo.RelatorioDeVendasVo ( "
				+"produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+"JOIN pedido.itens item "
				+"JOIN item.produto produto "
				+"GROUP BY produto.nome "
				+"ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioDeVendasVo.class)
				.getResultList();
	}
}
