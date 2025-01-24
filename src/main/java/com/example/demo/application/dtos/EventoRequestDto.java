package com.example.demo.application.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.demo.domain.models.enums.Status;

import lombok.Data;

@Data
public class EventoRequestDto {

	private String name;
	private String descricao;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private String local;
	private Integer limiteParticipantes;
	private Status status;
	private Set<ParticipanteResponseDto> participantes;
}
