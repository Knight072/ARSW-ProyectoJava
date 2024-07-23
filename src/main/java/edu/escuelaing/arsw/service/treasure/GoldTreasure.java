package edu.escuelaing.arsw.service.treasure;

/**
 * The GoldTreasure class represents a specific type of treasure in the game, namely a gold treasure.
 * It extends the abstract Treasure class and provides the specific characteristics for gold treasures.
 */
public class GoldTreasure extends Treasure {

    /**
     * Constructs a GoldTreasure instance with a predefined score.
     * Initializes the position of the treasure on the game table to a random location that is either empty or occupied by a police officer or thief.
     */
    public GoldTreasure() {
        this.score = 200; // Set the score value specific to GoldTreasure

        // Randomly place the gold treasure on the table
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

