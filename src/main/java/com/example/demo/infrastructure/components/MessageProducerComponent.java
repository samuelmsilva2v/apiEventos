package com.example.demo.infrastructure.components;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.models.entities.Participante;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageProducerComponent {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Queue queue;

	public void send(Participante participante) {
		try {
			var json = objectMapper.writeValueAsString(participante);
			rabbitTemplate.convertAndSend(queue.getName(), json);
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
