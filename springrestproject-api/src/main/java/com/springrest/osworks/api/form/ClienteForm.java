package com.springrest.osworks.api.form;

import javax.validation.constraints.NotNull;

public class ClienteForm {

	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
