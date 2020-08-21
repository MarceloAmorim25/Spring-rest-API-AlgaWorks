package com.springrest.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.osworks.api.dto.OrdemServicoDto;
import com.springrest.osworks.api.form.OrdemServicoForm;
import com.springrest.osworks.domain.model.OrdemServico;
import com.springrest.osworks.domain.repository.OrdemServicoRepository;
import com.springrest.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoDto criar(@Valid @RequestBody OrdemServicoForm ordemServicoForm) {
		
		OrdemServico ordemServico = toEntity(ordemServicoForm);
		
		return toDto(gestaoOrdemServico.criar(ordemServico));
		
	}
	

	@GetMapping
	public List<OrdemServicoDto> listar(){
		
		return toCollectionDto(ordemServicoRepository.findAll());
		
	}
	
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoDto> buscar(@PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		
		if(ordemServico.isPresent()) {
			OrdemServicoDto mappedDto = toDto(ordemServico.get());
			
			return ResponseEntity.ok(mappedDto);
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long ordemServicoId) {
		
		gestaoOrdemServico.finalizar(ordemServicoId);
		
	}
	
	private OrdemServicoDto toDto(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoDto.class);
	}
	
	private List<OrdemServicoDto> toCollectionDto(List<OrdemServico> ordensServico){		
		return ordensServico.stream()
				.map(ordemServico -> toDto(ordemServico))
				.collect(Collectors.toList());	
	}
	
	private OrdemServico toEntity(OrdemServicoForm ordemServicoForm) {
		return modelMapper.map(ordemServicoForm, OrdemServico.class);
	}
	
}
