package edu.escuelaing.arsw.service.treasure;

import edu.escuelaing.arsw.service.Table;
import edu.escuelaing.arsw.service.actor.Actor;

import java.util.HashMap;
import java.util.Random;

/**
 * The Treasure class represents a general treasure in the game.
 * It provides the base functionality and attributes for all specific types of treasures.
 */
public class Treasure {

    /** The game table instance where treasures are placed. */
    protected Table table = Table.getInstance();

    /** The identifier number for this type of treasure. */
    protected Integer number = 4;

    /** The score associated with the treasure. */
    protected Integer score;

    /** The X-coordinate position of the treasure on the table. */
    protected Integer positionX;

    /** The Y-coordinate position of the treasure on the table. */
    protected Integer positionY;

    /** Random number generator used for placing the treasure. */
    protected Random rand = new Random();

    /** Static map to store players (not used in this class but inherited by subclasses). */
    protected static HashMap<String, Actor> players;

    /**
     * Returns the type of the treasure as a string.
     * The type is determined by the class name.
     *
     * @return The simple name of the class (e.g., "GoldTreasure").
     */
    public String getType() {
        return this.getClass().getSimpleName();
    }

    /**
     * Returns the score associated with the treasure.
     *
     * @return The score value of the treasure.
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Returns the X-coordinate position of the treasure.
     *
     * @return The X-coordinate of the treasure's position.
     */
    public Integer getPositionX() {
        return positionX;
    }

    /**
     * Returns the Y-coordinate position of the treasure.
     *
     * @return The Y-coordinate of the treasure's position.
     */
    public Integer getPositionY() {
        return positionY;
    }
}

