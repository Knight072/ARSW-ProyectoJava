package edu.escuelaing.arsw.service.factory;

import edu.escuelaing.arsw.service.actor.Actor;
import edu.escuelaing.arsw.service.actor.Police;
import edu.escuelaing.arsw.service.actor.Thief;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The ActorFactory class is responsible for creating instances of different types of actors.
 * It implements the ActorFactoryMethod interface to provide a method for creating actors based on their type.
 */
@Component
public class ActorFactory implements ActorFactoryMethod {

    /**
     * Creates an actor of the specified type.
     *
     * <p>Generates a unique ID for the actor and initializes the actor based on the provided type.
     * It then adds the created actor to the static map of players and returns it.
     *
     * @param tipoActor the type of actor to create (e.g., 2 for Police, 3 for Thief)
     * @return the created actor
     */
    public Actor createActor(int tipoActor) {
        // Generate a unique ID for the actor
        String id = UUID.randomUUID().toString();

        Actor actor;

        // Create the appropriate type of actor based on the tipoActor parameter
        if (tipoActor == 3) {
            actor = new Thief(id);
        } else if (tipoActor == 2) {
            actor = new Police(id);
        } else {
            throw new IllegalArgumentException("Invalid actor type: " + tipoActor);
        }

        // Add the created actor to the static map of players
        actor.getPlayers().put(id, actor);

        // Return the created actor
        return Actor.getPlayers().get(id);
    }
}

