package dev.lydtech.dispatch.config;

import dev.lydtech.dispatch.message.OrderCreated;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@ComponentScan(basePackages = {"dev.lydtech"})
@Configuration
@EnableKafka
public class DispatchConfiguration {

    @Value("${spring.kafka.properties.bootstrap.servers}")
    String bootstrapServers;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(ConsumerFactory<String, Object> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(3); // Adjust the number of listener threads as needed.
        factory.getContainerProperties().setPollTimeout(3000); // Adjust the poll timeout as needed.
        return factory;
    }


    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        config.put(ConsumerConfig.GROUP_ID_CONFIG, "spring-ccloud");

        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, OrderCreated.class.getCanonicalName());

        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
        config.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        config.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        config.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"PKW66QSXF4KLJSPU\" password=\"SfyxLApygzWOXZRopYZSeKrj7bHvPru62g2Ehs9MZVjkmUaPh+i+96bxZBSCj2sQ\";");


        return new DefaultKafkaConsumerFactory<>(config);
    }

//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//            Map<String, Object> configs = new HashMap<>();
//            configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//
//            configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
//            configs.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
//            configs.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"PKW66QSXF4KLJSPU\" password=\"SfyxLApygzWOXZRopYZSeKrj7bHvPru62g2Ehs9MZVjkmUaPh+i+96bxZBSCj2sQ\";");
//
//        return new KafkaAdmin(configs);
//    }
}
