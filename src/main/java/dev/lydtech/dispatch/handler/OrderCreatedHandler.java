package dev.lydtech.dispatch.handler;

import dev.lydtech.dispatch.config.TopicConfig;
import dev.lydtech.message.OrderCreated;
import dev.lydtech.dispatch.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedHandler {

    private final DispatchService dispatchService;

    @KafkaListener(
            id = "orderConsumerClient",
            topics = TopicConfig.ORDER_CREATED_TOPIC,
            groupId = "dispatch.order.created.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(OrderCreated payload) {
        log.info("Received message payload : " + payload);
        try {
            dispatchService.process(payload);
        } catch (Exception e) {
            log.error("Processing Failure",e);
        }
    }

}
