package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiEventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEventosApplication.class, args);
	}

}

//Próximos Passos:
//1. Criar entidades no pacote domain.model (Evento, Participante).
//2. Criar interfaces de repositório no pacote domain.repository.
//3. Configurar PostgreSQL no pacote infrastructure.config.
//4. Criar esqueleto para serviços no pacote application.service.