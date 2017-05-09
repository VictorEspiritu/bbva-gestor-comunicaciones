package com.bbva.microservices.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbva.microservices.component.KafkaComponent;
import com.bbva.microservices.entity.Message;

@RestController
@RequestMapping("/api/mail")
public class APIMailController {
		
	private KafkaComponent kafkaComponent;
	
	@Autowired
	public APIMailController(KafkaComponent KafkaComponent) {
		this.kafkaComponent = KafkaComponent;
	}
	
	@RequestMapping( value="/add", method=RequestMethod.POST)
	String getSatus(@RequestBody Message message){
		message.setId(UUID.randomUUID().toString());
		kafkaComponent.putMessageOne(message);
		return message.getId();
	}
}
