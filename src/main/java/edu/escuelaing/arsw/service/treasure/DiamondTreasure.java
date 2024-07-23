package edu.escuelaing.arsw.service.treasure;

/**
 * The DiamondTreasure class represents a specific type of treasure in the game, namely a diamond treasure.
 * It extends the abstract Treasure class and provides the specific characteristics for diamond treasures.
 */
public class DiamondTreasure extends Treasure {

    /**
     * Constructs a DiamondTreasure instance with a predefined score.
     * Initializes the position of the treasure on the game table to a random location that is either empty or occupied by a police officer or thief.
     */
    public DiamondTreasure() {
        this.score = 300; // Set the score value specific to DiamondTreasure

        // Randomly place the diamond treasure on the table
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

