package com.bbva.microservices.componet;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaComponent {

	private static Logger log = LoggerFactory.getLogger(KafkaComponent.class);

	private Properties props;

	private AtomicBoolean closed;
	
	@Autowired
	private SenderComponent senderComponent;

	@Value("${kafka.host}")
	private String host;
	@Value("${kafka.topic}")
	private String topic;
	@Value("${kafka.auto.commit}")
	private String autoCommit;
	@Value("${kafka.commit.interval}")
	private String commitInterval;
	@Value("${kafka.group.id.config}")
	private String groupId;
	@Value("${kafka.serializer.key}")
	private String serializerKey;
	@Value("${kafka.serializer.value}")
	private String serializerValue;
	
	public KafkaComponent() {
		// TODO Auto-generated constructor stub
	}


	public void consumeMessage() {
		
		log.info("Loading Kafak Config");

		props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, commitInterval);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, serializerKey);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, serializerValue);
		closed = new AtomicBoolean(false);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				log.info("Shutting down");
				closed.set(true);
			}
		});

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList(topic));

		log.info("Reciviendo Mensaje");
		while (!closed.get()) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				log.info("Partition = {}  offset = {}  key = {}  value = {} :::", record.partition(),
						record.offset(), record.key(), record.value());
				
				log.info("Reciviendo informacion de Kafka");
				senderComponent.processMail(record.value());
			}
		}

		consumer.close();
	}
//
//	public void putMessage(Integer count) {
//		
//		Properties props1 = new Properties();
//		props1.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
//		props1.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializerKeyIn);
//		props1.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializerValueIn);
//        props1.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, partitionClass);
//
//        Producer<String, String> producer = new KafkaProducer<>(props1);
//
//        for (int i = 0; i < count; i++) {
//            String key = String.format("key[%d]", i);
//            String message = String.format("message[%d]", i);
//            log.info("Sending message with: {}" , key);
//            producer.send(new ProducerRecord<>(topic, key, message));
//            try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				log.warn("WARN : {}", e.getMessage());
//			}
//        }
//
//        producer.flush();
//        producer.close();
//	}
}
