package com.example.demo.application.dtos;

import lombok.Data;

@Data
public class DeleteParticipanteResponseDto {

	private ParticipanteResponseDto participante;
	private String mensagem;

	public DeleteParticipanteResponseDto(ParticipanteResponseDto participante, String mensagem) {
		this.participante = participante;
		this.mensagem = mensagem;
	}
}
