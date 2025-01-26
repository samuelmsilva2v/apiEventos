package com.example.demo.application.dtos;

import java.util.Date;

import com.example.demo.domain.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EventoRequestDto {

	@NotBlank(message = "O nome do evento é obrigatório")
	@Size(message = "O nome do evento deve ter entre 2 e 50 caracteres", min = 2, max = 50)
	private String nome;

	@Size(message = "A descrição do evento deve ter no máximo 255 caracteres", max = 255)
	private String descricao;

	@NotNull(message = "A data de início do evento é obrigatória")
	@FutureOrPresent(message = "A data de início do evento deve ser no presente ou no futuro")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataInicio;

	@NotNull(message = "A data de início do evento é obrigatória")
	@FutureOrPresent(message = "A data de fim do evento deve ser no presente ou no futuro")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataFim;

	@NotBlank(message = "O local do evento é obrigatório")
	@Size(message = "O local do evento deve ter entre 3 e 100 caracteres", min = 3, max = 100)
	private String local;
	
	@NotNull(message = "O limite de participantes do evento é obrigatório")
	private Integer limiteParticipantes;
	
	@NotNull(message = "O status do evento é obrigatório")
	private Status status;
}
