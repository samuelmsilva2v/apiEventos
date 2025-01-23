package com.example.demo.domain.models.entities;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.example.demo.domain.models.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@Column(length = 100)
	private String local;
	
	@Column(name = "limite_participantes")
	private Integer limiteParticipantes;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy = "evento")
	private Set<Participante> participantes;
}