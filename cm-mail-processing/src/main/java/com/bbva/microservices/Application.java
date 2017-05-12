package com.bbva.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.bbva.microservices.componet.KafkaComponent;

//@EnableDiscoveryClient
@SpringBootApplication
public class Application implements CommandLineRunner {

	private static Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private KafkaComponent kafkaComponent;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("Init Processs Listener Kafka");
		
		Thread th = new Thread() {
			public void run() {
				log.info("Receive Message Kafka");
				 kafkaComponent.consumeMessage();
			}
		};
		
		th.start();
	}
}
