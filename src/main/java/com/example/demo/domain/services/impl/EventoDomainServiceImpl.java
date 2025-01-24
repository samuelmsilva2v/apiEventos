package com.example.demo.domain.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.EventoRequestDto;
import com.example.demo.application.dtos.EventoResponseDto;
import com.example.demo.domain.models.entities.Evento;
import com.example.demo.domain.models.enums.Status;
import com.example.demo.domain.services.interfaces.EventoDomainService;
import com.example.demo.infrastructure.repositories.EventoRepository;

@Service
public class EventoDomainServiceImpl implements EventoDomainService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public EventoResponseDto cadastrarEvento(EventoRequestDto request) {

		var evento = modelMapper.map(request, Evento.class);
		evento.setId(UUID.randomUUID());

		eventoRepository.save(evento);

		return modelMapper.map(evento, EventoResponseDto.class);
	}

	@Override
	public EventoResponseDto atualizarEvento(UUID id, EventoRequestDto request) {

		var evento = eventoRepository.findById(id).get();

		evento.setNome(request.getNome());
		evento.setDescricao(request.getDescricao());
		evento.setDataInicio(request.getDataInicio());
		evento.setDataFim(request.getDataFim());
		evento.setLocal(request.getLocal());
		evento.setLimiteParticipantes(request.getLimiteParticipantes());
		evento.setStatus(request.getStatus());

		eventoRepository.save(evento);

		return modelMapper.map(evento, EventoResponseDto.class);
	}

	@Override
	public EventoResponseDto deletarEvento(UUID id) {

		var evento = eventoRepository.findById(id).get();

		eventoRepository.delete(evento);

		return modelMapper.map(evento, EventoResponseDto.class);
	}

	@Override
	public EventoResponseDto buscarEventoPorId(UUID id) {

		var evento = eventoRepository.findById(id).get();

		return modelMapper.map(evento, EventoResponseDto.class);
	}

	@Override
	public List<EventoResponseDto> consultarEventos() {

		return eventoRepository.findAll().stream().map(evento -> modelMapper.map(evento, EventoResponseDto.class))
				.toList();
	}

	@Override
	public List<EventoResponseDto> consultarEventosPorPeriodo(Date dataInicio, Date dataFim) {

		return eventoRepository.buscarPorPeriodo(dataInicio, dataFim).stream()
				.map(evento -> modelMapper.map(evento, EventoResponseDto.class)).toList();

	}

	@Override
	public List<EventoResponseDto> consultarEventosPorStatus(Status status) {

		return eventoRepository.buscarPorStatus(status).stream()
				.map(evento -> modelMapper.map(evento, EventoResponseDto.class)).toList();
	}

	@Override
	public long contarEventos() {
		
		return eventoRepository.count();
	}

}
