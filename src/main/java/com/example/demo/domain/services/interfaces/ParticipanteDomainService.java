package com.example.demo.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.application.dtos.ParticipanteRequestDto;
import com.example.demo.application.dtos.ParticipanteResponseDto;

public interface ParticipanteDomainService {

	ParticipanteResponseDto cadastrarParticipante(ParticipanteRequestDto request);

	ParticipanteResponseDto atualizarParticipante(UUID id, ParticipanteRequestDto request);

	ParticipanteResponseDto deletarParticipante(UUID id);

	ParticipanteResponseDto buscarParticipantePorId(UUID id);

	List<ParticipanteResponseDto> consultarParticipantes();
}
