package com.springrest.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.api.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/cliente")
	public List<Cliente> listar() {
		
		var cliente1 = new Cliente();
		
		cliente1.setId(1L);
		cliente1.setNome("Teste1");
		cliente1.setTelefone("123456789");
		
		var cliente2 = new Cliente();
		
		cliente2.setId(2L);
		cliente2.setNome("Teste2");
		cliente2.setTelefone("1231231231");
		
		return Arrays.asList(cliente1, cliente2);
		
	}
}
