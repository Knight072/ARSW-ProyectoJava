package edu.escuelaing.arsw.service.actor;

import edu.escuelaing.arsw.service.Table;

import java.util.Random;

/**
 * The Thief class represents a thief actor in the game. It extends the
 * Actor class and provides specific behavior for thief actors.
 */
public class Thief extends Actor {

    /**
     * Random number generator used to initialize the thief's position.
     */
    private final Random rand = new Random();

    /**
     * Constructs a Thief instance with the specified ID. Initializes the thief's
     * position on the game table to a random location that is either empty or
     * occupied by a police officer or a treasure.
     *
     * @param id the unique identifier of the thief actor
     */
    public Thief(String id) {
        this.id = id;
        this.number = 3; // Unique number assigned to thief actors
        this.table = Table.getInstance();

        // Randomly place the thief on the table
        do {
            this.positionX = rand.nextInt(9); // Random X position
            this.positionY = rand.nextInt(9); // Random Y position
        } while (table.getTable()[positionX][positionY] != 0 // Empty spot
                && table.getTable()[positionX][positionY] != 2 // Police
                && table.getTable()[positionX][positionY] != 4); // Treasure

        // Set the thief's position on the table
        table.getTable()[positionX][positionY] = number;
    }

    /**
     * Moves the thief actor to a new position. Clears the current position on the
     * table, removes a treasure if the new position contains one, updates the thief's
     * position, and sets the new position on the table.
     *
     * @param newPositionX the new X position of the thief
     * @param newPositionY the new Y position of the thief
     */
    @Override
    public void move(Integer newPositionX, Integer newPositionY) {
        // Clear the current position
        this.table.getTable()[positionX][positionY] = 0;

        // If moving to a treasure, remove it
        if (this.table.getTable()[newPositionX][newPositionY] == 4) {
            this.table.getTreasure().remove(newPositionX + Integer.toString(newPositionY));
        }

        // Update position
        this.positionX = newPositionX;
        this.positionY = newPositionY;

        // Set new position on the table
        this.table.getTable()[positionX][positionY] = number;
    }
}

