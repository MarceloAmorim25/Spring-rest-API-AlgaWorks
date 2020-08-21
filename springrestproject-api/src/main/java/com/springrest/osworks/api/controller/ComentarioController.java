package com.springrest.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.osworks.api.dto.ComentarioDto;
import com.springrest.osworks.api.form.ComentarioForm;
import com.springrest.osworks.domain.model.Comentario;
import com.springrest.osworks.domain.model.OrdemServico;
import com.springrest.osworks.domain.repository.OrdemServicoRepository;
import com.springrest.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<ComentarioDto> listar(@PathVariable Long ordemServicoId){
		OrdemServico ordemServico = ordemServicoRepository
				.findById(ordemServicoId)
				.orElseThrow();
		
		return toCollectionDto(ordemServico.getComentarios());
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioDto adicionar(@PathVariable Long ordemServicoId,
			@Valid @RequestBody ComentarioForm comentarioForm) {
		
		Comentario comentario = gestaoOrdemServico.adicionarComentario(ordemServicoId,
				comentarioForm.getDescricao());
		
		return toDto(comentario);
	}
	
	private ComentarioDto toDto(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioDto.class);
	}
	
	private List<ComentarioDto> toCollectionDto(List<Comentario> comentarios) {

		return comentarios.stream()
				.map(comentario -> toDto(comentario))
				.collect(Collectors.toList());
		
	}
	
}
