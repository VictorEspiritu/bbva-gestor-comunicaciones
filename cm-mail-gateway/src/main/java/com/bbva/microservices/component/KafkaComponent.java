package com.bbva.microservices.component;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.bbva.microservices.entity.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RefreshScope
public class KafkaComponent {

	private static Logger log = LoggerFactory.getLogger(KafkaComponent.class);

	@Value("${kafka.host}")
	private String host;
	@Value("${kafka.topic.mail}")
	private String mailTopic;
	@Value("${kafka.topic.mail.attach}")
	private String mailAttachTopic;
	@Value("${kafka.partition.class}")
	private String partitionClass;
	@Value("${kafka.serializer.key.in}")
	private String serializerKeyIn;
	@Value("${kafka.serializer.value.in}")
	private String serializerValueIn;

	public KafkaComponent() {
		// TODO Auto-generated constructor stub
	}

	public void putMessage(Message message) {

		Properties props1 = new Properties();
		props1.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
		props1.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializerKeyIn);
		props1.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializerValueIn);
		props1.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, partitionClass);

		Producer<String, String> producer = new KafkaProducer<>(props1);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(message);
			log.info("Sending message with: {}", jsonInString);
			producer.send(new ProducerRecord<>(mailTopic, message.getId(), jsonInString));

		} catch (JsonProcessingException e) {
			log.info("Error Parse to JSON {}", e.getMessage());
		}
		
		producer.flush();
		producer.close();
	}
	
	public void putMessageAttach(Message message) {

		Properties props1 = new Properties();
		props1.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
		props1.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializerKeyIn);
		props1.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializerValueIn);
		props1.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, partitionClass);

		Producer<String, String> producer = new KafkaProducer<>(props1);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(message);
			log.info("Sending message with Attachment: {}", jsonInString);
			producer.send(new ProducerRecord<>(mailAttachTopic, message.getId(), jsonInString));

		} catch (JsonProcessingException e) {
			log.info("Error Parse to JSON {}", e.getMessage());
		}
		
		producer.flush();
		producer.close();
	}
}
