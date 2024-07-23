package edu.escuelaing.arsw.service;

import edu.escuelaing.arsw.service.treasure.Treasure;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * The TableService class provides a service layer for interacting with the Table.
 * It handles operations related to the game board and treasures.
 */
@Service
public class TableService {
    /** The instance of the Table. */
    private Table table;

    /**
     * Constructs a TableService and initializes the Table instance.
     */
    public TableService() {
        this.table = Table.getInstance();
    }

    /**
     * Retrieves the game board and ensures that treasures are created.
     *
     * @return A 2D array representing the game board.
     */
    public Integer[][] getTable() {
        table.createTreasure();
        return table.getTable();
    }

    /**
     * Creates treasures and places them on the table.
     */
    public void createTreasures() {
        table.createTreasure();
    }

    /**
     * Retrieves the map of treasures currently on the table.
     *
     * @return A map of treasures, indexed by their position.
     */
    public HashMap<String, Treasure> getTreasures() {
        return table.getTreasure();
    }
}

