package com.bbva.microservices.componet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bbva.microservices.dto.Email;
import com.bbva.microservices.dto.Response;
import com.bbva.microservices.entity.Message;
import com.bbva.microservices.entity.Template;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SenderComponent {
	
	private static Logger log = LoggerFactory.getLogger(SenderComponent.class);
	
	@Value("${url.service.sender}")
	private String urlSender;
	
	@Autowired
	private MailComponent mailComponent;
	
	private RestTemplate restTemplate;
		
	public void processMail(String data) {
		
		ObjectMapper mapper = new ObjectMapper();
		Message message = null;
		try {
			message = mapper.readValue(data, Message.class);
		} catch (JsonParseException e) {
			log.info("Error JSON PARSE {}", e.getMessage());
		} catch (JsonMappingException e) {
			log.info("Error JSON EXCEPTION {}", e.getMessage());
		} catch (IOException e) {
			log.info("Error JSON IO {}", e.getMessage());
		}		
		
		if(message == null)
			return;

		String chTemplate = "N";
		try {
			Long id = message.getIdTemplate();
			Template mail = mailComponent.getTemplate(id);
			Template tmp = new Template();
			BeanUtils.copyProperties(mail, tmp);
			chTemplate = tmp.getTemplate();
			
			chTemplate= chTemplate.replace("$$title$$",message.getMail().getmTitle());
			chTemplate= chTemplate.replace("$$body$$", message.getMail().getmBody());
			chTemplate= chTemplate.replace("$$sign$$", message.getMail().getmSign());
			chTemplate= chTemplate.replace("$$disclamer$$", "BBVA Continental - Area de Ventas");
			
			log.info("Send Mailing");

			sendMail(message, chTemplate);
		} catch (Exception e) {
			log.info("ERROR: {}", e.getMessage());
		}
		
		log.info("Message Parse: {}", chTemplate);
		
	}
	
	private void sendMail(Message message, String mail){
		restTemplate = new RestTemplate();
		
		Email email = new Email();
		email.setFrom(message.getMail().getFromMail());
		email.setTo(Arrays.asList(message.getMail().getToMail()));
		email.setCc(message.getMail().getCcMail());
		email.setBcc(new ArrayList<String>());
		email.setSubject(message.getMail().getSubject());
		email.setHtmlContent(mail);
		
		log.info("E-Mail send: {}", email);
		
		Response rpta = restTemplate.postForObject(urlSender, email, Response.class);
		
		log.info("Response sender: {}", rpta);
	}
}
