package edu.escuelaing.arsw.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import edu.escuelaing.arsw.configuration.ActorApplicationContextAware;
import edu.escuelaing.arsw.repository.TicketRepository;
import edu.escuelaing.arsw.service.Table;
import edu.escuelaing.arsw.configuration.CustomConfigurator;
import edu.escuelaing.arsw.service.ActorService;
import edu.escuelaing.arsw.service.actor.Actor;
import jakarta.annotation.PreDestroy;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;

@Component
@ServerEndpoint(value = "/ActorEndpoint",
        encoders = ActorEncoder.class,
        decoders = ActorDecoder.class,
        configurator = CustomConfigurator.class)
public class ActorEndpoint {

    private static final Logger logger = Logger.getLogger(ActorEndpoint.class.getName());
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    private static final ConcurrentHashMap<Session, BlockingQueue<Object>> messagesToSend = new ConcurrentHashMap<>();

    Session ownSession = null;
    private boolean accepted = false;

    //This code allows to include a bean directly from the application context
    TicketRepository ticketRepo = (TicketRepository)
            ActorApplicationContextAware.getApplicationContext().getBean("ticketRepository");

    private final ActorService actorService;

    @Autowired
    public ActorEndpoint(ActorService actorService) {
        this.actorService = actorService;
    }

    @OnMessage
    public void handleMessage(JsonNode jsonNode, Session session) {
        logger.log(Level.INFO, "Received message: " + jsonNode.toString());
            scheduledExecutorService.submit(() -> {
                try {
                    processMessage(jsonNode, session);
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Se perdió la conexión con el cliente", e);
                    try {
                        session.close();
                    } catch (IOException closeException) {
                        logger.log(Level.SEVERE, "Error al cerrar la sesión", closeException);
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error processing message", e);
                }
            });
    }

    private void processMessage(JsonNode jsonNode, Session session) throws EncodeException, IOException {
        if (jsonNode.has("tipoActor")) {
            createActor(jsonNode.get("tipoActor").asInt(), session);
        } else if (jsonNode.has("id") && jsonNode.has("positionX") && jsonNode.has("positionY")) {
            makeMove(jsonNode, session);
        } else if (jsonNode.has("getGameState")) {
            sendGameState(session);
        } else {
            queueMessage(session, "Invalid message format");
        }
    }

    private void sendGameState(Session session) throws IOException, EncodeException {
        logger.log(Level.INFO, "Preparing game state to send");
        HashMap<String, Object> gameState = new HashMap<>();
        gameState.put("treasures", Table.getInstance().getTreasure());
        gameState.put("players", Table.getInstance().getTable());
        logger.log(Level.INFO, "Game state prepared: " + gameState.toString());
        queueMessage(session, gameState);
        logger.log(Level.INFO, "Game state queued for sending");
    }

    private void createActor(int tipoActor, Session session) throws EncodeException, IOException {
        Actor createdActor = actorService.createActor(tipoActor);
        for (Session s : sessions) {
            queueMessage(s, createdActor);
        }
        queueMessage(session, Table.getInstance().getTable());
    }

    private void makeMove(JsonNode moveData, Session session) throws IOException, EncodeException {
        String id = moveData.get("id").asText();
        int positionX = moveData.get("positionX").asInt();
        int positionY = moveData.get("positionY").asInt();

        actorService.moveActor(positionX, positionY, id);

        Integer[][] updatedTable = Table.getInstance().getTable();

        for (Session s : sessions) {
            queueMessage(s, updatedTable);
        }
    }

    private void queueMessage(Session session, Object message) {
        messagesToSend.computeIfAbsent(session, k -> new LinkedBlockingQueue<>()).offer(message);
        // Programar el envío de mensajes cada 1 segundo
        scheduledExecutorService.scheduleAtFixedRate(() -> sendQueuedMessages(session), 0, 1, TimeUnit.SECONDS);
    }

    private void sendQueuedMessages(Session session) {
        BlockingQueue<Object> queue = messagesToSend.get(session);
        if (queue == null) return;

        while (!queue.isEmpty()) {
            try {
                Object message = queue.poll();
                if (message != null) {
                    logger.log(Level.INFO, "Sending message: " + message.toString());
                    synchronized (session) {
                        session.getBasicRemote().sendObject(message);
                    }
                    logger.log(Level.INFO, "Message sent successfully");
                }
            } catch (IOException | EncodeException e) {
                logger.log(Level.SEVERE, "Error sending message", e);
                break;
            }
        }
    }

    @OnOpen
    public void openConnection(Session session) {
        sessions.add(session);
        ownSession = session;
        messagesToSend.put(session, new LinkedBlockingQueue<>());
        logger.log(Level.INFO, "Connection opened: " + session.getId());
        session.setMaxIdleTimeout(120000); // 2 minutos
        queueMessage(session, "Connection established.");
        queueMessage(session, Table.getInstance().getTable());
    }

    @OnClose
    public void closedConnection(Session session) {
        sessions.remove(session);
        messagesToSend.remove(session);
        logger.log(Level.INFO, "Connection closed: " + session.getId());
    }

    @OnError
    public void error(Session session, Throwable t) {
        sessions.remove(session);
        messagesToSend.remove(session);
        logger.log(Level.SEVERE, "Connection error: " + session.getId(), t);
    }

    @PreDestroy
    public void cleanup() {
        scheduledExecutorService.shutdown();
        try {
            if (!scheduledExecutorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                scheduledExecutorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduledExecutorService.shutdownNow();
        }
    }

    public static Queue<Session> getSessions() {
        return sessions;
    }
}




