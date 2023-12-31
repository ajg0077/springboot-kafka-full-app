package dev.lydtech.dispatch.handler;

import dev.lydtech.dispatch.service.DispatchService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

class OrderCreatedHandlerTest {

    private OrderCreatedHandler orderCreatedHandler;

    private DispatchService dispatchServiceMock;

    @BeforeEach
    void setUp() {
        dispatchServiceMock = mock(DispatchService.class);
        orderCreatedHandler = new OrderCreatedHandler(dispatchServiceMock);
    }

//    @Test
//    void listen_Success() throws Exception {
//        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
//        orderCreatedHandler.listen(testEvent);
//        verify(dispatchServiceMock, times(1)).process(testEvent);
//    }
//
//    @Test
//    void listen_ServiceThrowsExcetion() throws Exception {
//        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
//        doThrow(new RuntimeException("Service failure")).when(dispatchServiceMock).process(testEvent);
//        orderCreatedHandler.listen(testEvent);
//        verify(dispatchServiceMock, times(1)).process(testEvent);
//    }
}