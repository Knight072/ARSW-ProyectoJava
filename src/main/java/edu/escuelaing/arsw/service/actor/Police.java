package edu.escuelaing.arsw.service.actor;

import edu.escuelaing.arsw.service.Table;

import java.util.Random;

/**
 * The Police class represents a police actor in the game. It extends the
 * Actor class and provides specific behavior for police actors.
 */
public class Police extends Actor {

    /**
     * Random number generator used to initialize the police's position.
     */
    private Random rand = new Random();

    /**
     * Constructs a Police instance with the specified ID. Initializes the police's
     * position on the game table to a random location that is either empty or
     * occupied by a thief or another police officer.
     *
     * @param id the unique identifier of the police actor
     */
    public Police(String id) {
        this.id = id;
        this.number = 2; // Unique number assigned to police actors
        this.table = Table.getInstance();

        // Randomly place the police on the table
        do {
            this.positionX = rand.nextInt(9); // Random X position
            this.positionY = rand.nextInt(19); // Random Y position
        } while (table.getTable()[positionX][positionY] != 0 // Empty spot
                && table.getTable()[positionX][positionY] != 3 // Thief
                && table.getTable()[positionX][positionY] != 4); // Treasure

        // Set the police's position on the table
        table.getTable()[positionX][positionY] = number;
    }

    /**
     * Moves the police actor to a new position. Clears the current position on the
     * table, removes a treasure if the new position contains one, updates the police's
     * position, and sets the new position on the table.
     *
     * @param newPositionX the new X position of the police
     * @param newPositionY the new Y position of the police
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

