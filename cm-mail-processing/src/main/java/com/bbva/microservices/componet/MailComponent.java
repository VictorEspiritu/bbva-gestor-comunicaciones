package com.bbva.microservices.componet;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.bbva.microservices.entity.Message;
import com.bbva.microservices.entity.Template;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MailComponent {

	private static final Logger log = LoggerFactory.getLogger(MailComponent.class);

	@Value("${url.file.server}")
	private String urlFileServer;
	@Value("${template.mail.title}")
	private String templateTitle;
	@Value("${template.mail.body}")
	private String templateBody;
	@Value("${template.mail.sign}")
	private String templateSign;
	@Value("${template.mail.disclaimer}")
	private String templateDisc;
	

	@Autowired
	private TemplateComponent templateComponent;

	@Autowired
	private SenderComponent senderComponent;

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

		if (message == null)
			return;

		String chTemplate = "";
		try {
			Long id = message.getIdTemplate();
			Template mail = templateComponent.getTemplate(id);
			Template tmp = new Template();
			BeanUtils.copyProperties(mail, tmp);
			chTemplate = tmp.getTemplate();

			chTemplate = chTemplate.replace(templateTitle, message.getMail().getmTitle());
			chTemplate = chTemplate.replace(templateBody, message.getMail().getmBody());
			chTemplate = chTemplate.replace(templateSign, message.getMail().getmSign());
			chTemplate = chTemplate.replace(templateDisc, "BBVA Continental - Area de Ventas");

			log.info("Send Mailing");

			senderComponent.sendMail(message, chTemplate);

		} catch (Exception e) {
			log.info("ERROR: {}", e.getMessage());
		}

		log.info("Message Parse: {}", chTemplate);
	}

	public void processMailAttach(String data) {

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

		if (message == null)
			return;

		String chTemplate = "";
		try {
			Long id = message.getIdTemplate();
			Template mail = templateComponent.getTemplate(id);
			Template tmp = new Template();
			BeanUtils.copyProperties(mail, tmp);
			chTemplate = tmp.getTemplate();

			chTemplate = chTemplate.replace(templateTitle, message.getMail().getmTitle());
			chTemplate = chTemplate.replace(templateBody, message.getMail().getmBody());
			chTemplate = chTemplate.replace(templateSign, message.getMail().getmSign());
			chTemplate = chTemplate.replace(templateDisc, "BBVA Continental - Area de Ventas");

			log.info("Send Mailing");

			String[] datos = message.getMail().getNameAttachment().split("\\.");

			FileSystemResource obj = new FileSystemResource(
					urlFileServer + message.getId() + "." + datos[datos.length - 1]);
			log.info("Name File Attach {} : {}", obj.getFile().getAbsolutePath(), obj.getFile().exists());

			senderComponent.sendMailAttachment(message, chTemplate, obj);

		} catch (Exception e) {
			log.info("ERROR: {}", e.getMessage());
		}

		log.info("Message Parse: {}", chTemplate);
	}

}
