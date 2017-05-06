package com.bbva.microservices.component;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplePartitioner implements Partitioner{

    private static final Logger log = LoggerFactory.getLogger(SimplePartitioner.class);

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        log.info("Partition: {}", (Math.abs(key.hashCode() % cluster.partitionCountForTopic(topic))));
        return Math.abs(key.hashCode() % cluster.partitionCountForTopic(topic));
    }

    @Override
    public void close() {
        log.info("Close Partition");
    }

    @Override
    public void configure(Map<String, ?> map) {
        log.info("Configure Partition");
    }
}
