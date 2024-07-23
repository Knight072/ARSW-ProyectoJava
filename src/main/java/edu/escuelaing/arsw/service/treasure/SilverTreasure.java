package edu.escuelaing.arsw.service.treasure;

/**
 * The SilverTreasure class represents a specific type of treasure in the game, namely a silver treasure.
 * It extends the abstract Treasure class and provides the specific characteristics for silver treasures.
 */
public class SilverTreasure extends Treasure {

    /**
     * Constructs a SilverTreasure instance with a predefined score.
     * Initializes the position of the treasure on the game table to a random location that is either empty or occupied by a police officer or thief.
     */
    public SilverTreasure() {
        this.score = 100; // Set the score value specific to SilverTreasure

        // Randomly place the silver treasure on the table
        do {
            this.positionX = rand.nextInt(9); // Random X position
            this.positionY = rand.nextInt(19); // Random Y position
        } while (table.getTable()[positionX][positionY] != 0 // Empty spot
                && table.getTable()[positionX][positionY] != 2 // Police
                && table.getTable()[positionX][positionY] != 3); // Thief

        // Place the treasure on the table
        table.getTable()[positionX][positionY] = number;
    }
}

