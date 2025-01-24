package com.example.demo.domain.models.entities;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import com.example.demo.domain.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String nome;
	private String descricao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "data_inicio", columnDefinition = "TIMESTAMP")
	private Date dataInicio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "data_fim", columnDefinition = "TIMESTAMP")
	private Date dataFim;

	
	@Column(length = 100)
	private String local;
	
	@Column(name = "limite_participantes")
	private Integer limiteParticipantes;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy = "evento")
	private Set<Participante> participantes;
}