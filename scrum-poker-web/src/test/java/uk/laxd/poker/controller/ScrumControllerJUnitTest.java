package uk.laxd.poker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import uk.laxd.poker.dto.UserDto;

import java.util.Arrays;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by lawrence on 10/02/16.
 */
public class ScrumControllerJUnitTest {

    private static final String DESTINATION_PREFIX = "/poker";

    private TestMessageChannel outbound;
    private TestMessageChannel inbound;
    private TestMessageChannel brokerQueue;

    private TestAnnotationMethodHandler annotationMethodHandler;

    @Before
    public void setUp() throws Exception {
        this.inbound = new TestMessageChannel();
        this.outbound = new TestMessageChannel();
        this.brokerQueue = new TestMessageChannel();

        annotationMethodHandler = new TestAnnotationMethodHandler(
                inbound,
                outbound,
                new SimpMessagingTemplate(brokerQueue));

        ScrumController controller = new ScrumController();

        this.annotationMethodHandler.registerHandler(controller);
        this.annotationMethodHandler.setDestinationPrefixes(Arrays.asList(DESTINATION_PREFIX));
        this.annotationMethodHandler.setMessageConverter(new MappingJackson2MessageConverter());
        this.annotationMethodHandler.setApplicationContext(new StaticApplicationContext());
        this.annotationMethodHandler.afterPropertiesSet();
    }

    @Test
    public void testAddUserNotifiesAllUsersOfNewUser() throws Exception {
        StompHeaderAccessor headers = createHeaders("/addUser");

        UserDto user = new UserDto();
        user.setName("Dave");
        byte[] payload = new ObjectMapper().writeValueAsBytes(user);

        Message<byte[]> message = MessageBuilder.withPayload(payload).setHeaders(headers).build();

        this.annotationMethodHandler.handleMessage(message);

        assertEquals(1, this.brokerQueue.getMessages().size());

        Message<?> response = this.brokerQueue.getMessages().get(0);

        StompHeaderAccessor responseHeaders = StompHeaderAccessor.wrap(response);

        assertEquals("/topic/userConnect", responseHeaders.getDestination());
    }

    private StompHeaderAccessor createHeaders(String destination) {
        StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SUBSCRIBE);
        headers.setSubscriptionId("0");
        headers.setDestination(DESTINATION_PREFIX + destination);
        headers.setSessionId("0");
        headers.setSessionAttributes(new HashMap<>());

        return headers;
    }

    private static class TestAnnotationMethodHandler extends SimpAnnotationMethodMessageHandler {

        public TestAnnotationMethodHandler(SubscribableChannel i, MessageChannel o,
                                           SimpMessageSendingOperations t) {
            super(i, o, t);
        }

        public void registerHandler(Object handler) {
            super.detectHandlerMethods(handler);
        }
    }
}


