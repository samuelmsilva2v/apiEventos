package com.example.demo.domain.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.EventoRequestDto;
import com.example.demo.application.dtos.EventoResponseDto;
import com.example.demo.domain.services.interfaces.EventoDomainService;

@Service
public class EventoDomainServiceImpl implements EventoDomainService{

	@Override
	public EventoResponseDto cadastrarEvento(EventoRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventoResponseDto atualizarEvento(UUID id, EventoRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventoResponseDto deletarEvento(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventoResponseDto buscarEventoPorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventoResponseDto> consultarEventos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventoResponseDto> consultarEventosPorPeriodo(Date dataInicio, Date dataFim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventoResponseDto> consultarEventosPorStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer contarEventos() {
		// TODO Auto-generated method stub
		return 0;
	}

}
