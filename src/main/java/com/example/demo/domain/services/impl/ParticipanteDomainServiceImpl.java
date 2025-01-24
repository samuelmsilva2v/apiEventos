package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.ParticipanteRequestDto;
import com.example.demo.application.dtos.ParticipanteResponseDto;
import com.example.demo.domain.services.interfaces.ParticipanteDomainService;

@Service
public class ParticipanteDomainServiceImpl implements ParticipanteDomainService {

	@Override
	public ParticipanteResponseDto cadastrarParticipante(ParticipanteRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParticipanteResponseDto atualizarParticipante(UUID id, ParticipanteRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParticipanteResponseDto deletarParticipante(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParticipanteResponseDto buscarParticipantePorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParticipanteResponseDto> consultarParticipantes() {
		// TODO Auto-generated method stub
		return null;
	}

}
