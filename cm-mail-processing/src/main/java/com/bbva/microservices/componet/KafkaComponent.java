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
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class KafkaComponent {

	private static final Logger log = LoggerFactory.getLogger(KafkaComponent.class);

	private Properties props;

	private AtomicBoolean closed;

	@Autowired
	private MailComponent mailComponent;

	@Value("${kafka.host}")
	private String host;
	@Value("${kafka.topic.mail}")
	private String mailTopic;
	@Value("${kafka.topic.mail.attach}")
	private String mailAttachTopic;
	@Value("${kafka.auto.commit}")
	private String autoCommit;
	@Value("${kafka.message.commit.interval}")
	private String messageCommitInterval;
	@Value("${kafka.message.pool.interval}")
	private Long messagePoolInterval;
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

		log.info("Loading Kafka Config");

		props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, messageCommitInterval);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, serializerKey);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, serializerValue);
		closed = new AtomicBoolean(false);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				log.info("Shutting down Customer Kafka");
				closed.set(true);
			}
		});

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList(mailTopic));

		log.info("Message Receiving Kafka");
		while (!closed.get()) {
			ConsumerRecords<String, String> records = consumer.poll(messagePoolInterval);
			for (ConsumerRecord<String, String> record : records) {
				log.info("Partition = {}  offset = {}  key = {}  value = {} :::", record.partition(), record.offset(), record.key(), record.value());
				mailComponent.processMail(record.value());
			}
		}
		consumer.close();
	}	
}
