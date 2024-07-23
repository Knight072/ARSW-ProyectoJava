package edu.escuelaing.arsw.service.factory;

import edu.escuelaing.arsw.service.actor.Actor;

/**
 * The ActorFactoryMethod interface defines a contract for creating actors in the game.
 * Implementing classes must provide a method for creating actors of a specific type.
 */
public interface ActorFactoryMethod {

    /**
     * Creates an actor of the specified type.
     *
     * <p>This method takes an integer representing the type of actor to create
     * and returns an instance of the created actor.
     *
     * @param tipoActor the type of actor to create (e.g., 2 for Police, 3 for Thief)
     * @return the created actor
     */
    Actor createActor(int tipoActor);
}

