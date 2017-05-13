#Init install package
ZOOKEEPER=zookeeper-3.4.10
KAFKA=kafka_2.11-0.10.2.0

#Config hostname cluster
sudo cp /vagrant_data/hosts /etc/hosts

#Install Java
echo 'Install Java ++++++++++++'
sudo add-apt-repository -y ppa:webupd8team/java
sudo apt-get update
sudo echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections
sudo apt-get install -y oracle-java8-installer

#environment path
export JAVA_HOME=/usr/lib/jvm/java-8-oracle/

#Instalamos Zookeeper
sudo wget http://www-eu.apache.org/dist/zookeeper/stable/$ZOOKEEPER.tar.gz
sudo tar -xvf $ZOOKEEPER.tar.gz
sudo mv $ZOOKEEPER/conf/zoo_sample.cfg $ZOOKEEPER/conf/zoo.cfg

#Instalamos Kafka
sudo wget http://www-eu.apache.org/dist/kafka/0.10.2.0/$KAFKA.tgz
sudo tar -xvf $KAFKA.tgz
#ls -l /tmp/kafka-logs/
# create topic:
# sudo kafka_2.11-0.10.2.0/bin/kafka-topics.sh --create --partition 3 --replication-factor 1 --topic mailAttchTopic --zookeeper zook0:2181

