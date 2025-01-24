package com.example.demo.domain.services.interfaces;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.demo.application.dtos.EventoRequestDto;
import com.example.demo.application.dtos.EventoResponseDto;
import com.example.demo.domain.models.enums.Status;

public interface EventoDomainService {

	EventoResponseDto cadastrarEvento(EventoRequestDto request);
	
	EventoResponseDto atualizarEvento(UUID id, EventoRequestDto request);
	
	EventoResponseDto deletarEvento(UUID id);
	
	EventoResponseDto buscarEventoPorId(UUID id);
	
	List<EventoResponseDto> consultarEventos();
	
	List<EventoResponseDto> consultarEventosPorPeriodo(Date dataInicio, Date dataFim);
	
	List<EventoResponseDto> consultarEventosPorStatus(Status status);
	
	long contarEventos();
}
