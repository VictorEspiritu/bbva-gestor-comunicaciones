# Communication Manager
Communication manager that allows to handle the different messages (sms, mail, push, socket, etc) source from different systems

## Approach
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


## Components
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


## Installation
1. Run Docker Container exposing port 5672 with RabbitMQ.
2. Run Vagrant to build instances 3 Kafka and 1 Zookeeper.
3. Create a Git repository (Local/Internet) and copy the files from Config-Repository folder here.
4. Review the properties and change some values that you consider.
5. Run ConfigService.
6. Run EurekaService.
7. Run Mail APIGateway.
8. Run Mail Processing.
9. Enjoy it ;).


#### Start
```javascript
$ mvn clean package
...
[INFO] --- spring-boot-maven-plugin:1.5.3.RELEASE:repackage (default) @ cm-eureka-server ---
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] cm-bbva-project ................................... SUCCESS [0.002s]
[INFO] cm-mail-gateway ................................... SUCCESS [3.527s]
[INFO] cm-mail-processing ................................ SUCCESS [0.776s]
[INFO] cm-config-server .................................. SUCCESS [13.845s]
[INFO] cm-eureka-server .................................. SUCCESS [21.594s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 40.175s
[INFO] Finished at: Thu May 18 14:30:08 COT 2017
[INFO] Final Memory: 31M/358M
[INFO] ------------------------------------------------------------------------

```
