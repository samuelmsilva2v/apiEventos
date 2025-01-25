package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		var evento = eventoRepository.findById(request.getIdEvento()).get();

		var participante = modelMapper.map(request, Participante.class);
		participante.setId(UUID.randomUUID());
		participante.setEvento(evento);

		participanteRepository.save(participante);

		return modelMapper.map(participante, ParticipanteResponseDto.class);
	}

	@Override
	public ParticipanteResponseDto atualizarParticipante(UUID id, ParticipanteRequestDto request) {

		var participante = participanteRepository.findById(id).get();
		participante.setNome(request.getNome());
		participante.setTelefone(request.getTelefone());
		participante.setEmail(request.getEmail());

		var evento = eventoRepository.findById(request.getIdEvento()).get();
		participante.setEvento(evento);

		participanteRepository.save(participante);

		return modelMapper.map(participante, ParticipanteResponseDto.class);
	}

	@Override
	public ParticipanteResponseDto deletarParticipante(UUID id) {

		var participante = participanteRepository.findById(id).get();

		participanteRepository.delete(participante);

		return modelMapper.map(participante, ParticipanteResponseDto.class);
	}

	@Override
	public ParticipanteResponseDto buscarParticipantePorId(UUID id) {

		var participante = participanteRepository.findById(id).get();

		return modelMapper.map(participante, ParticipanteResponseDto.class);
	}

	@Override
	public List<ParticipanteResponseDto> consultarParticipantes() {

		return participanteRepository.findAll().stream()
				.map(participante -> modelMapper.map(participante, ParticipanteResponseDto.class)).toList();
	}
}
