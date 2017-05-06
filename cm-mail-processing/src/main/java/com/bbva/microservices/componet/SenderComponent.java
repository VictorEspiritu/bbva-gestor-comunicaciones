package com.bbva.microservices.componet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbva.microservices.entity.Template;

@Component
public class SenderComponent {
	
	private static Logger log = LoggerFactory.getLogger(SenderComponent.class);
	
	@Autowired
	private MailComponent mailComponent;
		
	public Template processMail(String data) {
		
//		ObjectMapper mapper = new ObjectMapper();
//		Message message = null;
//		try {
//			message = mapper.readValue(data, Message.class);
//		} catch (JsonParseException e) {
//			log.info("Error JSON PARSE {}", e.getMessage());
//		} catch (JsonMappingException e) {
//			log.info("Error JSON EXCEPTION {}", e.getMessage());
//		} catch (IOException e) {
//			log.info("Error JSON IO {}", e.getMessage());
//		}		
//		
//		if(message == null)
//			return;
//		
//		Long id = message.getIdTemplate();
		Long id = 2L;
		Template mail = mailComponent.getTemplate(id);
		Template tmp = new Template();
		BeanUtils.copyProperties(mail, tmp);
		String chTemplate = tmp.getTemplate();
		
		chTemplate= chTemplate.replace("$$TITLE$$", "HOLASSS");
		
		log.info("Message Parse: {}", chTemplate);
		
		return mail;
	}
	
}
