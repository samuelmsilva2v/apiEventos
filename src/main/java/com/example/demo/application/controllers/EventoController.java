package com.example.demo.application.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.dtos.DeleteEventoResponseDto;
import com.example.demo.application.dtos.EventoRequestDto;
import com.example.demo.application.dtos.EventoResponseDto;
import com.example.demo.domain.models.enums.Status;
import com.example.demo.domain.services.interfaces.EventoDomainService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

	@Autowired
	private EventoDomainService eventoDomainService;

	@PostMapping
	EventoResponseDto post(@Valid @RequestBody EventoRequestDto request) {
		return eventoDomainService.cadastrarEvento(request);
	}

	@PutMapping("{id}")
	EventoResponseDto put(@PathVariable UUID id, @Valid @RequestBody EventoRequestDto request) {
		return eventoDomainService.atualizarEvento(id, request);
	}

	@DeleteMapping("{id}")
	DeleteEventoResponseDto delete(@PathVariable UUID id) {
		return eventoDomainService.excluirEvento(id);
	}

	@GetMapping("{id}")
	EventoResponseDto getById(@PathVariable UUID id) {
		return eventoDomainService.buscarEventoPorId(id);
	}

	@GetMapping
	List<EventoResponseDto> getAll() {
		return eventoDomainService.consultarEventos();
	}

	@GetMapping("/period")
	List<EventoResponseDto> getByPeriod(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
		return eventoDomainService.consultarEventosPorPeriodo(startDate, endDate);
	}

	@GetMapping("/status")
	List<EventoResponseDto> getByStatus(Status status) {
		return eventoDomainService.consultarEventosPorStatus(status);
	}

	@GetMapping("/count")
	long count() {
		return eventoDomainService.contarEventos();
	}
}
