package com.example.demo.application.dtos;

import java.util.Date;
import java.util.UUID;

import com.example.demo.domain.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EventoResponseDto {

	private UUID id;
	private String nome;
	private String descricao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataInicio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataFim;
    
	private String local;
	private Integer limiteParticipantes;
	private Status status;
}
