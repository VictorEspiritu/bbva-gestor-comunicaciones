package com.bbva.microservices.componet;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.bbva.microservices.dto.Email;
import com.bbva.microservices.dto.Response;
import com.bbva.microservices.entity.Message;
import com.bbva.microservices.proxy.MailServiceProxy;

@EnableFeignClients("com.bbva.microservices.proxy")
@RefreshScope
@Component
public class SenderComponent {
	
	private static Logger log = LoggerFactory.getLogger(SenderComponent.class);
	
	@Value("${url.service.sender}")
	private String urlSender;
	
	@Autowired
	private MailServiceProxy mailServiceProxy;
	
	public void sendMail(Message message, String mail){
		
		Email email = new Email();
		email.setFrom(message.getMail().getFromMail());
		email.setTo(Arrays.asList(message.getMail().getToMail()));
		email.setCc(message.getMail().getCcMail() == null ? new ArrayList<>() : message.getMail().getCcMail());
		email.setBcc(new ArrayList<String>());
		email.setSubject(message.getMail().getSubject());
		email.setHtmlContent(mail);
		
		log.info("E-Mail send: {}", email);
		
		Response rpta = mailServiceProxy.postEmail(email);
		
		log.info("Response sender: {}", rpta);
	}
	
	public void sendMailAttachment(Message message, String mail, FileSystemResource file){
		
		Email email = new Email();
		email.setFrom(message.getMail().getFromMail());
		email.setTo(Arrays.asList(message.getMail().getToMail()));
		email.setCc(message.getMail().getCcMail() == null ? new ArrayList<>() : message.getMail().getCcMail());
		email.setBcc(new ArrayList<String>());
		email.setSubject(message.getMail().getSubject());
		email.setHtmlContent(mail);
		
		log.info("E-Mail send: {}", email);
		
		Response rpta = mailServiceProxy.postEmail(email);
		
		log.info("Response sender: {}", rpta);
	}
}
