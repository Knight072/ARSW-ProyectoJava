package edu.escuelaing.arsw.controller;

import edu.escuelaing.arsw.service.treasure.Treasure;
import edu.escuelaing.arsw.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * The TableController class is a REST controller that handles HTTP requests related
 * to the game table and treasures. It provides endpoints for retrieving the game
 * table, creating treasures, and retrieving treasures.
 *
 * <p>It is marked with the @RestController annotation to indicate that it is a Spring
 * REST controller, and @CrossOrigin to allow cross-origin requests. The @RequestMapping
 * annotation is used to specify the base path for all endpoints in this controller.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/table")
public class TableController {

    /**
     * The service used to manage the game table and treasures.
     */
    private final TableService tableService;

    /**
     * Constructs a TableController with the specified TableService.
     *
     * @param tableService the service used to manage the game table and treasures
     */
    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    /**
     * Retrieves the current state of the game table.
     *
     * @return a 2D array representing the game table
     */
    @GetMapping("")
    public Integer[][] getTable() {
        return tableService.getTable();
    }

    /**
     * Creates treasures on the game table.
     */
    @GetMapping("/treasures")
    public void createTreasures() {
        tableService.createTreasures();
    }

    /**
     * Retrieves all treasures on the game table.
     *
     * @return a HashMap containing the treasures, where the keys are the treasure IDs
     *         and the values are the Treasure objects
     */
    @GetMapping("/treasures/get")
    public HashMap<String, Treasure> getTreasures() {
        return tableService.getTreasures();
    }
}

