package br.com.jpa.loja.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.jpa.loja.modelo.Cliente;

public class ClienteDao {
	
	private EntityManager em;

	public ClienteDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadastrar (Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public Cliente buscarPorId(BigDecimal l) {
		return em.find(Cliente.class, l);
	}

	
	
}
