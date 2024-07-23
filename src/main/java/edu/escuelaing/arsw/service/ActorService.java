package edu.escuelaing.arsw.service;

import edu.escuelaing.arsw.service.actor.Actor;
import edu.escuelaing.arsw.service.factory.ActorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The ActorService class provides services related to managing actors in the game.
 * It interacts with the ActorFactory to create new actors and manages the movement of actors.
 */
@Service
public class ActorService {

    /** The factory used to create actors. */
    private ActorFactory actorFactory;

    /**
     * Constructs an ActorService instance with the specified ActorFactory.
     *
     * @param actorFactory The factory used to create actors.
     */
    @Autowired
    public ActorService(ActorFactory actorFactory) {
        this.actorFactory = actorFactory;
    }

    /**
     * Retrieves an actor by its unique ID.
     *
     * @param id The unique ID of the actor.
     * @return The Actor object corresponding to the specified ID.
     */
    public Actor getActor(String id) {
        return Actor.getPlayers().get(id);
    }

    /**
     * Creates a new actor of the specified type.
     *
     * @param tipoActor The type of actor to create (e.g., police or thief).
     * @return The newly created Actor object.
     */
    public Actor createActor(Integer tipoActor) {
        return actorFactory.createActor(tipoActor);
    }

    /**
     * Moves an actor to a new position on the game table.
     *
     * @param positionX The new X-coordinate position of the actor.
     * @param positionY The new Y-coordinate position of the actor.
     * @param id The unique ID of the actor to move.
     */
    public void moveActor(Integer positionX, Integer positionY, String id) {
        Actor.getPlayers().get(id).move(positionX, positionY);
    }
}

