package com.example.demo.domain.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.DeleteEventoResponseDto;
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

		if (eventoRepository.existsByNome(request.getNome())) {
			throw new IllegalArgumentException("Já existe um evento com o nome fornecido.");
		}

		var evento = modelMapper.map(request, Evento.class);
		evento.setId(UUID.randomUUID());

		if (evento.getDataInicio().after(evento.getDataFim())) {
			throw new IllegalArgumentException("A data de início do evento não pode ser posterior à data de fim.");
		}

		if (evento.getLimiteParticipantes() <= 0) {
			throw new IllegalArgumentException("O limite de participantes não pode ser negativo.");
		}

		if (evento.getLimiteParticipantes() < evento.getParticipantes().size()) {
			throw new IllegalArgumentException(
					"O limite de participantes não pode ser menor que o número de participantes.");
		}

		if (evento.getLimiteParticipantes() == 0) {
			throw new IllegalArgumentException("O limite de participantes não pode ser zero.");
		}

		eventoRepository.save(evento);

		return modelMapper.map(evento, EventoResponseDto.class);
	}

	@Override
	public EventoResponseDto atualizarEvento(UUID id, EventoRequestDto request) {

		var evento = eventoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Evento " + id + " não encontrado. Verifique o ID."));

		evento.setNome(request.getNome());
		evento.setDescricao(request.getDescricao());
		evento.setDataInicio(request.getDataInicio());
		evento.setDataFim(request.getDataFim());
		evento.setLocal(request.getLocal());
		evento.setLimiteParticipantes(request.getLimiteParticipantes());
		evento.setStatus(request.getStatus());

		if (evento.getDataInicio().after(evento.getDataFim())) {
			throw new IllegalArgumentException("A data de início do evento não pode ser posterior à data de fim.");
		}

		if (evento.getLimiteParticipantes() <= 0) {
			throw new IllegalArgumentException("O limite de participantes não pode ser negativo.");
		}

		if (evento.getLimiteParticipantes() < evento.getParticipantes().size()) {
			throw new IllegalArgumentException(
					"O limite de participantes não pode ser menor que o número de participantes.");
		}

		if (evento.getLimiteParticipantes() == 0) {
			throw new IllegalArgumentException("O limite de participantes não pode ser zero.");
		}

		eventoRepository.save(evento);

		return modelMapper.map(evento, EventoResponseDto.class);
	}

	@Override
	public DeleteEventoResponseDto excluirEvento(UUID id) {

		var evento = eventoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Evento " + id + " não encontrado. Verifique o ID."));

		eventoRepository.delete(evento);

		var eventoResponseDto = modelMapper.map(evento, EventoResponseDto.class);
		var mensagem = "Evento" + id + " excluído com sucesso.";

		return new DeleteEventoResponseDto(eventoResponseDto, mensagem);
	}

	@Override
	public EventoResponseDto buscarEventoPorId(UUID id) {

		var evento = eventoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Evento " + id + " não encontrado. Verifique o ID."));

		return modelMapper.map(evento, EventoResponseDto.class);
	}

	@Override
	public List<EventoResponseDto> consultarEventos() {

		return eventoRepository.findAll().stream().map(evento -> modelMapper.map(evento, EventoResponseDto.class))
				.toList();
	}

	@Override
	public List<EventoResponseDto> consultarEventosPorPeriodo(Date dataInicio, Date dataFim) {

		if (dataInicio == null || dataFim == null) {
			throw new IllegalArgumentException("As datas de início e fim não podem ser nulas.");
		}

		if (dataInicio.after(dataFim)) {
			throw new IllegalArgumentException("A data de início não pode ser posterior à data de fim.");
		}

		return eventoRepository.buscarPorPeriodo(dataInicio, dataFim).stream()
				.map(evento -> modelMapper.map(evento, EventoResponseDto.class)).toList();

	}

	@Override
	public List<EventoResponseDto> consultarEventosPorStatus(Status status) {
		
		if(status == null) {
            throw new IllegalArgumentException("O status não pode ser nulo.");
		}

		return eventoRepository.buscarPorStatus(status).stream()
				.map(evento -> modelMapper.map(evento, EventoResponseDto.class)).toList();
	}

	@Override
	public long contarEventos() {

		return eventoRepository.count();
	}

}
