package com.bbva.microservices.componet;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbva.microservices.entity.Message;
import com.bbva.microservices.entity.Template;
import com.bbva.microservices.repository.MailRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MailComponent {
	
	private static Logger log = LoggerFactory.getLogger(MailComponent.class);
	
	private MailRepository mailRepository;
	
	@Autowired
	public MailComponent(MailRepository mailRepository){
		this.mailRepository = mailRepository;
	}
	
	public void addTemplate(Template template){
		mailRepository.save(template);
	}
	
	public Template getTemplate(Long idTemplate){
		return mailRepository.findOne(idTemplate);
	}
	
}
