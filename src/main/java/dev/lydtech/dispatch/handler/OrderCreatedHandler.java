package dev.lydtech.dispatch.handler;

import dev.lydtech.dispatch.config.TopicConfig;
import dev.lydtech.message.OrderCreated;
import dev.lydtech.dispatch.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(
        id = "orderConsumerClient",
        topics = TopicConfig.ORDER_CREATED_TOPIC,
        groupId = "dispatch.order.created.consumer",
        containerFactory = "kafkaListenerContainerFactory"
)
public class OrderCreatedHandler {

    private final DispatchService dispatchService;


    @KafkaHandler
    public void listen(@Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_KEY) String key,
                       @Payload OrderCreated payload) {
        log.info("Received message - partition: "+partition+", key "+key+", payload : "+payload);
        try {
            dispatchService.process(key, payload);
        } catch (Exception e) {
            log.error("Processing Failure", e);
        }
    }

}
