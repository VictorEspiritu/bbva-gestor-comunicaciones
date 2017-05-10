package com.bbva.microservices.componet;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bbva.microservices.dto.Email;
import com.bbva.microservices.entity.Message;

@Component
@RefreshScope
public class SenderComponent {
	
	private static Logger log = LoggerFactory.getLogger(SenderComponent.class);
	
	@Value("${url.service.sender}")
	private String urlSender;
				
	private RestTemplate restTemplate;
	
	public void sendMail(Message message, String mail){
		restTemplate = new RestTemplate();
		
		Email email = new Email();
		email.setFrom(message.getMail().getFromMail());
		email.setTo(Arrays.asList(message.getMail().getToMail()));
		email.setCc(message.getMail().getCcMail() == null ? new ArrayList<>() : message.getMail().getCcMail());
		email.setBcc(new ArrayList<String>());
		email.setSubject(message.getMail().getSubject());
		email.setHtmlContent(mail);
		
		log.info("E-Mail send: {}", email);
		
//		Response rpta = restTemplate.postForObject(urlSender, email, Response.class);
		
		log.info("Response sender: {}", "");
	}
	
	public void sendMailAttachment(Message message, String mail, FileSystemResource file){
		restTemplate = new RestTemplate();
		
		Email email = new Email();
		email.setFrom(message.getMail().getFromMail());
		email.setTo(Arrays.asList(message.getMail().getToMail()));
		email.setCc(message.getMail().getCcMail() == null ? new ArrayList<>() : message.getMail().getCcMail());
		email.setBcc(new ArrayList<String>());
		email.setSubject(message.getMail().getSubject());
		email.setHtmlContent(mail);
		
		log.info("E-Mail send: {}", email);
		
//		Response rpta = restTemplate.postForObject(urlSender, email, Response.class);
		
		log.info("Response sender: {}", "");
	}
}
