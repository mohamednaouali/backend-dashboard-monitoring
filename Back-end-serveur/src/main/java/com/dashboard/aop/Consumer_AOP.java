package com.dashboard.aop;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * Request Response Client ------> AspectAspect Oriented-Programming
 */
@Aspect
@Component
public class Consumer_AOP {
    final static Logger logger = LoggerFactory.getLogger(Consumer_AOP.class);
    //private UserDetails userDetails;
    public Consumer_AOP() {
    }
    @Before("@annotation(com.dashboard.aop.CONS)")
    public void sendUserActivity(JoinPoint joinPoint) throws Throwable {
        java.lang.String groupId = "FirstApplication";
        java.lang.String topic = "userDetails";
        Properties prop = new Properties();
        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        prop.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //----------> Create kafka
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        consumer.subscribe(Arrays.asList(topic));
       // while (true) {
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                logger.info("Key___:" + record.key() + "\n");
                logger.info("Value___:" + record.value() + "\n");
                System.out.println( "value : " + record.value());
                logger.info("Topic___:" + record.topic() + "\n");
                logger.info("Offset___:" + record.offset() + "\n");
            }
        System.out.println("before method execution");
        }
   //
    public static void main(String[] args) {

    }
}


