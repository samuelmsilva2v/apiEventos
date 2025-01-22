package com.example.demo.domain.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eventos")
@Data
public class Evento {

	@Id
	private UUID id;
	
	@Column(length = 50, nullable = false)
	private String name;
	private String descricao;
	
	@Column(name = "data_inicio", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataInicio;
	@Column(name = "data_fim", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataFim;
	private String local;
	private Integer limite_participantes;
	// TODO private Status status;
}
