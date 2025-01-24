package com.example.demo.application.dtos;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.example.demo.domain.models.enums.Status;

import lombok.Data;

@Data
public class EventoResponseDto {

	private UUID id;
	private String nome;
	private String descricao;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private String local;
	private Integer limiteParticipantes;
	private Status status;
	private Set<ParticipanteResponseDto> participantes;
}
