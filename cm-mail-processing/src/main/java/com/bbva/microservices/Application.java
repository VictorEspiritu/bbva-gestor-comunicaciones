package com.bbva.microservices;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bbva.microservices.componet.KafkaComponent;
import com.bbva.microservices.componet.MailComponent;
import com.bbva.microservices.componet.SenderComponent;
import com.bbva.microservices.entity.Template;

@SpringBootApplication
public class Application implements CommandLineRunner{

	private static Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private SenderComponent senderComponent;
	
	@Autowired
	private MailComponent mailComponent;
	
	@Autowired
	private KafkaComponent kafkaComponent;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		log.info("Init Processs");
		
		Template t1 = new Template("Tmp_Public", new Date(), "<!DOCTYPE HTML PUBLIC  __TITLE__ -//W3C//DTD XHTML 1.0 Transitional //EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd><!--[if IE]><html xmlns=http://www.w3.rg/1999/xhtml class=ie-browserxmlns:v=urn:schemas-microsoft-com:vml xmlns:o=urn:schemas-microsoft-com:office:office><![endif]--><!--[if !IE]><!--><html tyle margin: 0;padding: 0;xmlns=http://www.w3.org/1999/xhtml><!--<![endif]--><head>     <!--[if gte mso 9]><xml>    <o:OfficeDocumentSettings>     <o:AllowPNG/>      <o:PixelsPerInch>96</o:PixelsPerInch>     </o:OfficeDocumentSettings>     </xml><![endif]-->     <meta http-equiv=Content-Type content=text/html;  set=utf- >   <meta name=viewport content=width=device-width>    <!--[if !mso]><!--><meta http-equiv=X-UA-Compatibl content=IE=edge><!--<![endif]-->    <title>plate Base</title>     <style type=text/css id=media-query>      body {   margin: 0;   padding: 0; }  table, tr, td {   vertical-align: top;   border-collapse: llapse; } ", 2L, "WALLET");
		Template t2 = new Template("Tmp_Personal", new Date(), "<html> $$TITLE$$ </html>", 2L, "WALLET");
		Template t3 = new Template("Tmp_direct", new Date(), "<html> ##TITLE## </html>", 2L, "WALLET");
		Template t4 = new Template("Tmp_anonymous",new Date(), "<html> ##TITLE## </html>", 3L, "GLOMO");
		Template t5 = new Template("Tmp_standar", new Date(), "<html> ##TITLE </html>", 1L, "ALL");
		
		mailComponent.addTemplate(t1);
		mailComponent.addTemplate(t2);
		mailComponent.addTemplate(t3);
		mailComponent.addTemplate(t4);
		mailComponent.addTemplate(t5);
		
		log.info("Save Tamplate: {}", t1);
		
		log.info("Init Receive Message Kafka");
//		kafkaComponent.consumeMessage();
		
		log.info("Init Send Message Kafka");
//		kafkaComponent.putMessage(100);
		
		senderComponent.processMail("Init");

		
	}
}
