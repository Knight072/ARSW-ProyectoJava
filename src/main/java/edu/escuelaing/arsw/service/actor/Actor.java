package edu.escuelaing.arsw.service.actor;

import edu.escuelaing.arsw.service.Table;

import java.util.HashMap;

/**
 * The Actor class is an abstract class representing an actor in the game.
 * It contains common properties and methods for all types of actors.
 */
public abstract class Actor {
    /**
     * The game table on which the actor is placed.
     */
    protected Table table;

    /**
     * The unique identifier of the actor.
     */
    protected String id;

    /**
     * The X position of the actor on the game table.
     */
    protected Integer positionX;

    /**
     * The Y position of the actor on the game table.
     */
    protected Integer positionY;

    /**
     * A unique number assigned to the actor.
     */
    protected Integer number;

    /**
     * A static map storing all players, keyed by their unique ID.
     */
    protected static HashMap<String, Actor> players;

    /**
     * Moves the actor to a new position. This method should be overridden
     * by subclasses to provide specific movement behavior.
     *
     * @param newPositionX the new X position of the actor
     * @param newPositionY the new Y position of the actor
     */
    public void move(Integer newPositionX, Integer newPositionY) {
        // Method should be implemented in subclasses
    }

    /**
     * Retrieves the map of players. If the map is not initialized, it creates a new
     * HashMap and assigns it to the players variable.
     *
     * @return the map of players
     */
    public static HashMap<String, Actor> getPlayers() {
        if (players == null) {
            return players = new HashMap<>();
        }
        return players;
    }

    /**
     * Gets the unique number assigned to the actor.
     *
     * @return the actor's unique number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the X position of the actor.
     *
     * @return the X position of the actor
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Gets the Y position of the actor.
     *
     * @return the Y position of the actor
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Gets the unique identifier of the actor.
     *
     * @return the actor's unique ID
     */
    public String getId() {
        return id;
    }
}

