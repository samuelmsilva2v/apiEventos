package com.example.demo.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.dtos.ParticipanteRequestDto;
import com.example.demo.application.dtos.ParticipanteResponseDto;
import com.example.demo.domain.services.interfaces.ParticipanteDomainService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {

	@Autowired
	private ParticipanteDomainService participanteDomainService;
	
	@PostMapping
	ParticipanteResponseDto post(@Valid @RequestBody ParticipanteRequestDto request) {
		return participanteDomainService.cadastrarParticipante(request);
	}
	
	@PutMapping("{id}")
	ParticipanteResponseDto put(@PathVariable UUID id, @Valid @RequestBody ParticipanteRequestDto request) {
		return participanteDomainService.atualizarParticipante(id, request);
	}
	
	@DeleteMapping("{id}")
	ParticipanteResponseDto delete(@PathVariable UUID id) {
		return participanteDomainService.deletarParticipante(id);
	}
	
	@GetMapping("{id}")
	ParticipanteResponseDto getById(@PathVariable UUID id) {
		return participanteDomainService.buscarParticipantePorId(id);
	}
	
	@GetMapping
	List<ParticipanteResponseDto> getAll() {
		return participanteDomainService.consultarParticipantes();
	}
}
