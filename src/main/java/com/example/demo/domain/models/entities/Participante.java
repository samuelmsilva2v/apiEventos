package com.example.demo.domain.models.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "participantes")
@Data
public class Participante {

	@Id
	private UUID id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 15, nullable = false, unique = true)
	private String telefone;
	
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "evento_id")
	private Evento evento;
}
