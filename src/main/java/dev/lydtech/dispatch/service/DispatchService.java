package dev.lydtech.dispatch.service;

import dev.lydtech.dispatch.config.TopicConfig;
import dev.lydtech.dispatch.message.OrderCreated;
import dev.lydtech.dispatch.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class DispatchService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void process(OrderCreated orderCreated) throws Exception {
        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderId())
                .build();

        kafkaTemplate.send(TopicConfig.ORDER_DISPATCHED_TOPIC, orderDispatched).get();
    }
}
