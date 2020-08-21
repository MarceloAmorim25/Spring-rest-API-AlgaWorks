package com.springrest.osworks.api.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class OrdemServicoForm {
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	private ClienteForm cliente;
	
	public ClienteForm getCliente() {
		return cliente;
	}
	public void setCliente(ClienteForm cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	
}
