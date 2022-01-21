package br.com.jpa.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.jpa.loja.dao.CategoriaDao;
import br.com.jpa.loja.dao.ProdutoDao;
import br.com.jpa.loja.modelo.Categoria;
import br.com.jpa.loja.modelo.Produto;
import br.com.jpa.loja.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		
		Produto p = dao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = dao.buscarTodos();
		todos.forEach(p2 -> System.out.println(p2.getNome()));
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("Celulares");
		Produto celular = new Produto("Xiaomi Redmi", "BOM", new BigDecimal("800"), celulares);
				
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		CategoriaDao Catdao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		Catdao.cadastrar(celulares);
		dao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}
}
