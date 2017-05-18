# Communication Manager

# Approach
- Microservices
- Service Discovery
- API Gateway
- Centralized configuration 
- Feign
- Ribbon
- Notification Bus
- Cluster Messaging  
- Containers
- Distributed monitoring
- Distributed Tracing 
- Log aggregation
- OAuth Security


# Components
- Config Server
- Eureka Server
- API Gateway Service
- Mail Process Service
- RabbitMQ
- Apache Kafka
- Logstash
- Kibana
- Elasticsearch
- Kubernetes


# Installation
- 1. Run Docker Container exposing port 5672 with RabbitMQ.
- 2. Run Vagrant to build instances 3 Kafka and 1 Zookeeper.
- 3. Create a Git repository (Local/Internet) and copy the files from Config-Repository folder here.  
- 4. Review the properties and change some values that you consider.
- 5. Run ConfigService.
- 6. Run EurekaService.
- 7. Run Mail APIGateway.
- 8. Run Mail Processing.
- 9. Enjoy it ;).

