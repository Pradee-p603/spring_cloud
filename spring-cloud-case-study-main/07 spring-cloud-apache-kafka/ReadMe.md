# Apache Kafka

Spring Kafka is the Spring integration for Apache Kafka, providing support for Kafka-based messaging in Spring applications. It simplifies the configuration and interaction with Kafka topics, producers, and consumers.

- download Apache Kafka - https://archive.apache.org/dist/kafka/3.5.0/kafka_2.12-3.5.0.tgz

**@KafkaListener:** This is an annotation provided by the Spring Kafka framework. It is used to mark a method as a listener for Kafka messages. When combined with appropriate configurations, it allows the method to receive messages from Kafka topics.

**topics = "my-topic":** This specifies the Kafka topic(s) that this method will listen to. In this case, it is set to "my-topic", meaning that the method will consume messages from the Kafka topic named "my-topic".

**groupId = "my-group":** This specifies the consumer group ID for the Kafka consumer. Consumer group ID is used by Kafka to identify different consumers that belong to the same consumer group. Each message in a topic is consumed by only one consumer within a consumer group. By specifying a group ID, you can have multiple instances of your application consuming messages from the same topic without each instance receiving all messages.

**KafkaTemplate:** KafkaTemplate is a Spring Kafka class that provides high-level abstraction for sending messages to Kafka topics.
