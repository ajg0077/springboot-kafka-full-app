package dev.lydtech.dispatch.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.kafka.core.KafkaTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

class DispatchServiceTest {

    private DispatchService dispatchService;
    private KafkaTemplate kafkaProducerMock;

    @BeforeEach
    void setUp() {
        kafkaProducerMock = mock(KafkaTemplate.class);
        dispatchService = new DispatchService(kafkaProducerMock);
    }

//    @Test
//    void process_Success() throws Exception {
//        when(kafkaProducerMock.send(anyString(),any(OrderDispatched.class))).thenReturn(mock(CompletableFuture.class));
//        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
//
//        dispatchService.process(testEvent);
//
//        verify(kafkaProducerMock, times(1)).send(eq("order.dispatched"),any(OrderDispatched.class));
//    }
//
//    @Test
//    void process_ProducerThrowsException() throws Exception {
//        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
//
//        doThrow(new RuntimeException("Producer failure")).when(kafkaProducerMock).send(eq("order.dispatched"),any(OrderDispatched.class));
//
//        Exception exception = assertThrows(RuntimeException.class, ()-> dispatchService.process(testEvent));
//        verify(kafkaProducerMock, times(1)).send(eq("order.dispatched"),any(OrderDispatched.class));
//        assertThat(exception.getMessage(), equalTo("Producer failure"));
//
//
//    }
}