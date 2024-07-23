package edu.escuelaing.arsw.service;

import edu.escuelaing.arsw.service.actor.Actor;
import edu.escuelaing.arsw.service.factory.TreasureFactory;
import edu.escuelaing.arsw.service.treasure.Treasure;

import java.util.*;

/**
 * The Table class represents the game board and handles the generation of the maze,
 * as well as the management of actors and treasures within the game.
 */
public class Table {

    /** Singleton instance of the Table. */
    private static Table INSTANCE;

    /** 2D array representing the game board. */
    private Integer[][] table = new Integer[10][20];

    /** A map of actors currently on the table, indexed by their IDs. */
    private HashMap<Integer, Actor> players = new HashMap<>();

    /** Random number generator for maze generation and other random tasks. */
    private final Random rand = new Random();

    /** A map of treasures currently on the table, indexed by their position. */
    private HashMap<String, Treasure> treasures;

    /**
     * Private constructor for singleton pattern. Initializes the maze.
     */
    private Table() {
        generateMaze();
    }

    /**
     * Gets the singleton instance of the Table.
     *
     * @return The singleton instance of the Table.
     */
    public static Table getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Table();
        }
        return INSTANCE;
    }

    /**
     * Generates the maze for the game board using Recursive Backtracking.
     */
    private void generateMaze() {
        // Initialize all cells as walls
        for (int i = 0; i < table.length; i++) {
            Arrays.fill(table[i], 1);
        }

        // Create the maze path
        createPath(1, 1);

        // Close the exit (make it a wall)
        table[table.length - 2][table[0].length - 2] = 1;

        // Add additional paths
        addAdditionalPaths();
    }

    /**
     * Recursively creates a path in the maze.
     *
     * @param x The current X coordinate.
     * @param y The current Y coordinate.
     */
    private void createPath(int x, int y) {
        table[x][y] = 0; // Mark the cell as part of the path

        // Directions in the order North, South, East, West
        Integer[] directions = {0, 1, 2, 3};
        Collections.shuffle(Arrays.asList(directions), rand);

        for (int i = 0; i < directions.length; i++) {
            switch (directions[i]) {
                case 0: // North
                    if (x - 2 > 0 && table[x - 2][y] == 1) {
                        table[x - 2][y] = 0;
                        table[x - 1][y] = 0;
                        createPath(x - 2, y);
                    }
                    break;
                case 1: // South
                    if (x + 2 < table.length - 1 && table[x + 2][y] == 1) {
                        table[x + 2][y] = 0;
                        table[x + 1][y] = 0;
                        createPath(x + 2, y);
                    }
                    break;
                case 2: // East
                    if (y + 2 < table[0].length - 1 && table[x][y + 2] == 1) {
                        table[x][y + 2] = 0;
                        table[x][y + 1] = 0;
                        createPath(x, y + 2);
                    }
                    break;
                case 3: // West
                    if (y - 2 > 0 && table[x][y - 2] == 1) {
                        table[x][y - 2] = 0;
                        table[x][y - 1] = 0;
                        createPath(x, y - 2);
                    }
                    break;
            }
        }
    }

    /**
     * Adds additional paths to the maze.
     * A wall has a 30% chance of being converted into a path.
     */
    private void addAdditionalPaths() {
        for (int i = 1; i < table.length - 1; i++) {
            for (int j = 1; j < table[0].length - 1; j++) {
                if (table[i][j] == 1 && rand.nextDouble() < 0.3) { // 30% chance
                    table[i][j] = 0;
                }
            }
        }
    }

    /**
     * Creates treasures and places them on the table.
     */
    public void createTreasure() {
        if (treasures == null) {
            TreasureFactory factory = new TreasureFactory(4);
            treasures = factory.getTreasures();
        }
    }

    /**
     * Gets the map of treasures currently on the table.
     *
     * @return A map of treasures, indexed by their position.
     */
    public HashMap<String, Treasure> getTreasure() {
        return treasures;
    }

    /**
     * Gets the game board as a 2D array.
     *
     * @return The 2D array representing the game board.
     */
    public Integer[][] getTable() {
        return table;
    }
}


