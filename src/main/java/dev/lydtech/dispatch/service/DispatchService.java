package dev.lydtech.dispatch.service;

import dev.lydtech.dispatch.config.TopicConfig;
import dev.lydtech.message.DispatchPreparing;
import dev.lydtech.message.OrderCreated;
import dev.lydtech.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void process(String key, OrderCreated orderCreated) throws Exception {
        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderId())
                .build();

        kafkaTemplate.send(TopicConfig.ORDER_DISPATCHED_TOPIC, key, orderDispatched).get();

        DispatchPreparing dispatchPreparing = DispatchPreparing.builder()
                .orderId(orderCreated.getOrderId())
                .build();

        kafkaTemplate.send(TopicConfig.DISPATCH_TRACKING_TOPIC, key, dispatchPreparing).get();

    }
}
