package com.dashboard.aop;

import com.dashboard.modal.UserDetails;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Request Response Client ------> AspectAspect Oriented-Programming
 */
@Aspect
@Component
public class Producer_AOP {
    @After("@annotation(com.dashboard.aop.PROD)")
    public void sendUserActivity(JoinPoint joinPoint) throws Throwable {
        final Logger logger = LoggerFactory.getLogger(Producer_AOP.class);

        /**
         *  Create producer property
         */
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        /**
         * Advanced Config  Create safe producer
         */
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
        /**
         *  Advanced Config In high throughput
         */
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

        Object[] objects = joinPoint.getArgs();
        UserDetails user = (UserDetails) objects[0];
        user.setUsername(user.getUsername());
        user.setPort(user.getPort());
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record =
                new ProducerRecord("mytopic", user.toString());

        /**
         *  Send data record
         */

        producer.send(record, (recordMetadata, e) -> {
            if (e == null) {
                logger.info(" Loading receiving data ... . Do not exit \n");
                logger.info("Topic :" + record.topic() + "\n");
                logger.info("Value :" + record.value() + "\n");
            } else {
                logger.error("Error while Producing ...  ", e);
            }

        });
        /**
         *   Shutdown producer after sent data
         */
        logger.info("Producer Flushing");
        producer.flush();
        logger.info("Closing Producer Process  ... ");
        producer.close();
        logger.info("Done ... Producer closed ");

    }
}



