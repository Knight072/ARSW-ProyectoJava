package edu.escuelaing.arsw;

import com.fasterxml.jackson.databind.JsonNode;
import edu.escuelaing.arsw.service.ActorService;
import edu.escuelaing.arsw.service.actor.Actor;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ServerEndpoint(value = "/ActorEndpoint",
        encoders = ActorEncoder.class,
        decoders = ActorDecoder.class,
        configurator = CustomConfigurator.class)
public class ActorEndpoint {

    private static final Logger logger = Logger.getLogger(ActorEndpoint.class.getName());

    /* Queue for all open WebSocket sessions */
    private static Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    private ActorService actorService;

    @Autowired
    public ActorEndpoint(ActorService actorService) {
        this.actorService = actorService;
    }

    public static Queue<Session> getSessions() {
        return sessions;
    }

    @OnMessage
    public void handleMessage(JsonNode jsonNode, Session session) throws EncodeException, IOException {
        if (jsonNode.has("tipoActor")) {
            createActor(jsonNode.get("tipoActor").asInt(), session);
        } else if (jsonNode.has("id") && jsonNode.has("positionX") && jsonNode.has("positionY")) {
            System.out.println(jsonNode);
            makeMove(jsonNode, session);
        } else if (jsonNode.has("getTable")) {
            session.getBasicRemote().sendObject(Table.getInstance().getTable());
        }else {
            session.getBasicRemote().sendText("Invalid message format");
        }
    }

    private void createActor(int tipoActor, Session session) throws EncodeException, IOException {
        Actor createdActor = actorService.createActor(tipoActor);
        for (Session s : sessions) {
            s.getBasicRemote().sendObject(createdActor);
            session.getBasicRemote().sendObject(Table.getInstance().getTable());
        }
    }

    private void makeMove(JsonNode moveData, Session session) throws IOException, EncodeException {
        String id = moveData.get("id").asText();
        int positionX = moveData.get("positionX").asInt();
        int positionY = moveData.get("positionY").asInt();

        actorService.moveActor(positionX, positionY, id);

        Integer[][] updatedTable = Table.getInstance().getTable();

        for (Session s : sessions) {
            s.getBasicRemote().sendObject(updatedTable);
        }
    }

    @OnOpen
    public void openConnection(Session session) {
        sessions.add(session);
        /* Register this connection in the queue */
        logger.log(Level.INFO, "Connection opened: " + session.getId());
        try {
            session.getBasicRemote().sendText("Connection established.");
            session.getBasicRemote().sendObject(Table.getInstance().getTable());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (EncodeException e) {
            throw new RuntimeException(e);
        }
    }

    @OnClose
    public void closedConnection(Session session) {
        /* Remove this connection from the queue */
        sessions.remove(session);
        logger.log(Level.INFO, "Connection closed: " + session.getId());
    }

    @OnError
    public void error(Session session, Throwable t) {
        /* Remove this connection from the queue */
        sessions.remove(session);
        logger.log(Level.INFO, t.toString());
        logger.log(Level.INFO, "Connection error: " + session.getId());
    }
}



