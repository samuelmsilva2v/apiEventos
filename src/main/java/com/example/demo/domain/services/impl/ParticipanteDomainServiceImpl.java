package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.DeleteParticipanteResponseDto;
import com.example.demo.application.dtos.ParticipanteRequestDto;
import com.example.demo.application.dtos.ParticipanteResponseDto;
import com.example.demo.domain.models.entities.Participante;
import com.example.demo.domain.services.interfaces.ParticipanteDomainService;
import com.example.demo.infrastructure.repositories.EventoRepository;
import com.example.demo.infrastructure.repositories.ParticipanteRepository;

@Service
public class ParticipanteDomainServiceImpl implements ParticipanteDomainService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ParticipanteRepository participanteRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public ParticipanteResponseDto cadastrarParticipante(ParticipanteRequestDto request) {

		var evento = eventoRepository.findById(request.getIdEvento()).orElseThrow(() -> new IllegalArgumentException(
				"Evento " + request.getIdEvento() + " não encontrado. Verifique o ID informado."));

		if (participanteRepository.existsByEmail(request.getEmail())) {
			throw new IllegalArgumentException(
					"Já existe um participante cadastrado com o e-mail: " + request.getEmail());
		}

		if (participanteRepository.existsByTelefone(request.getTelefone())) {
			throw new IllegalArgumentException(
					"Já existe um participante cadastrado com o telefone: " + request.getTelefone());
		}

		var participante = modelMapper.map(request, Participante.class);
		participante.setId(UUID.randomUUID());
		participante.setEvento(evento);

		participanteRepository.save(participante);

		return modelMapper.map(participante, ParticipanteResponseDto.class);
	}

	@Override
	public ParticipanteResponseDto atualizarParticipante(UUID id, ParticipanteRequestDto request) {

		var participante = participanteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
				"Participante " + id + " não encontrado. Verifique o ID informado."));

		if (participanteRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
			throw new IllegalArgumentException(
					"Já existe um participante cadastrado com o e-mail: " + request.getEmail());
		}

		if (participanteRepository.existsByTelefoneAndIdNot(request.getTelefone(), id)) {
			throw new IllegalArgumentException(
					"Já existe um participante cadastrado com o telefone: " + request.getTelefone());
		}

		participante.setNome(request.getNome());
		participante.setTelefone(request.getTelefone());
		participante.setEmail(request.getEmail());

		var evento = eventoRepository.findById(request.getIdEvento()).get();
		participante.setEvento(evento);

		participanteRepository.save(participante);

		return modelMapper.map(participante, ParticipanteResponseDto.class);
	}

	@Override
	public DeleteParticipanteResponseDto excluirParticipante(UUID id) {

		var participante = participanteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Participante " + id + " não encontrado"));

		participanteRepository.delete(participante);

		var response = modelMapper.map(participante, ParticipanteResponseDto.class);
		var mensagem = "Participante " + id + " excluído com sucesso";

		return new DeleteParticipanteResponseDto(response, mensagem);
	}

	@Override
	public ParticipanteResponseDto buscarParticipantePorId(UUID id) {

		var participante = participanteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
				"Participante " + id + " não encontrado. Verifique o ID informado."));

		return modelMapper.map(participante, ParticipanteResponseDto.class);
	}

	@Override
	public List<ParticipanteResponseDto> consultarParticipantes() {

		return participanteRepository.findAll().stream()
				.map(participante -> modelMapper.map(participante, ParticipanteResponseDto.class)).toList();
	}

	@Override
	public List<ParticipanteResponseDto> consultarParticipantesPorEvento(UUID eventoId) {

	    var evento = eventoRepository.findById(eventoId)
	            .orElseThrow(() -> new IllegalArgumentException("Evento " + eventoId + " não encontrado. Verifique o ID."));

	    var participantes = evento.getParticipantes();

	    if (participantes.isEmpty()) {
	        throw new IllegalArgumentException("Nenhum participante encontrado para o evento com ID " + eventoId + ".");
	    }

	    return participantes.stream()
	            .map(participante -> modelMapper.map(participante, ParticipanteResponseDto.class))
	            .toList();
	}


	@Override
	public String removerParticipanteDeEvento(UUID participanteId, UUID eventoId) {
		
		var evento = eventoRepository.findById(eventoId).orElseThrow(() -> new IllegalArgumentException(
				"Evento " + eventoId + " não encontrado. Verifique o ID informado."));
		
		var participante = evento.getParticipantes().stream()
	            .filter(p -> p.getId().equals(participanteId))
	            .findFirst()
	            .orElseThrow(() -> new IllegalArgumentException(
	                    "Participante " + participanteId + " não encontrado no evento " + eventoId + "."));
		
		evento.getParticipantes().remove(participante);
		
		eventoRepository.save(evento);
		
		return "Participante " + participanteId + " removido do evento " + eventoId + " com sucesso.";
	}

	@Override
	public long contarParticipantesPorEvento(UUID eventoId) {
		
		var evento = eventoRepository.findById(eventoId).orElseThrow(() -> new IllegalArgumentException(
				"Evento " + eventoId + " não encontrado. Verifique o ID informado."));
		
		return participanteRepository.countByEventoId(evento.getId());
	}
}
