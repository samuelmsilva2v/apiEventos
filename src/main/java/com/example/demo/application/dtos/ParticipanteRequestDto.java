package com.example.demo.application.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParticipanteRequestDto {

	@NotBlank(message = "O nome do participante é obrigatório")
	@Size(min = 3, max = 100, message = "O nome do participante deve ter entre 2 e 100 caracteres")
	private String nome;
	
	@NotBlank(message = "O telefone do participante é obrigatório")
	@Pattern(regexp = "\\d{2,3} \\d{5}-\\d{4}", message = "O telefone deve estar no formato DDD NNNNN-NNNN")
	private String telefone;
	
	@NotBlank(message = "O e-mail do participante é obrigatório")
	@Email(message = "O e-mail do participante deve ser válido")
	private String email;
	
	private UUID idEvento;
}
