package com.example.demo.application.dtos;

import lombok.Data;

@Data
public class DeleteEventoResponseDto {

	private EventoResponseDto evento;
	private String mensagem;
	
	public DeleteEventoResponseDto(EventoResponseDto evento, String mensagem) {
		super();
		this.evento = evento;
		this.mensagem = mensagem;
	}
}
