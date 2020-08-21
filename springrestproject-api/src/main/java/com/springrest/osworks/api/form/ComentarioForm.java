package com.springrest.osworks.api.form;

import javax.validation.constraints.NotBlank;

public class ComentarioForm {
	
	@NotBlank
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
