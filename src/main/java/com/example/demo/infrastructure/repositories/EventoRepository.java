package com.example.demo.infrastructure.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.models.entities.Evento;
import com.example.demo.domain.models.enums.Status;

public interface EventoRepository extends JpaRepository<Evento, UUID> {

	@Query("SELECT e FROM Evento e WHERE e.dataInicio >= :dataInicio AND e.dataFim <= :dataFim")
	List<Evento> buscarPorPeriodo(@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
	
	@Query("SELECT e FROM Evento e WHERE e.status = :status")
	List<Evento> buscarPorStatus(@Param("status") Status status);

	long count();
	
	boolean existsByNome(String nome);
}
