package edu.escuelaing.arsw.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.escuelaing.arsw.configuration.ActorApplicationContextAware;
import edu.escuelaing.arsw.repository.TicketRepository;
import edu.escuelaing.arsw.service.ActorService;
import edu.escuelaing.arsw.service.actor.Actor;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActorEndpointTest {

    private ActorEndpoint actorEndpoint;

    @Mock
    private ActorService actorService;

    @Mock
    private Session session;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private ApplicationContext applicationContext;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Set up the ApplicationContext
        when(applicationContext.getBean("ticketRepository")).thenReturn(ticketRepository);

        // Create a new instance of ActorApplicationContextAware and set the context
        ActorApplicationContextAware contextAware = new ActorApplicationContextAware();
        contextAware.setApplicationContext(applicationContext);

        actorEndpoint = new ActorEndpoint(actorService);
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
        // Reset the ApplicationContext after each test
        new ActorApplicationContextAware().setApplicationContext(null);
    }

    @Test
    void testOpenConnection() {
        actorEndpoint.openConnection(session);

        Queue<Session> sessions = ActorEndpoint.getSessions();
        assertTrue(sessions.contains(session));
    }

    @Test
    void testClosedConnection() {
        ActorEndpoint.getSessions().add(session);

        actorEndpoint.closedConnection(session);

        Queue<Session> sessions = ActorEndpoint.getSessions();
        assertFalse(sessions.contains(session));
    }
}
