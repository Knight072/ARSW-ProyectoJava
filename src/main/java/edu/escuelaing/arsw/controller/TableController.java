package edu.escuelaing.arsw.controller;

import edu.escuelaing.arsw.service.treasure.Treasure;
import edu.escuelaing.arsw.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin("*")
@RequestMapping("/table")
public class TableController {
    private TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("")
    public Integer[][] getTable() {
        return tableService.getTable();
    }

    @GetMapping("/treasures")
    public void createTreasures() {
        tableService.createTreasures();
    }

    @GetMapping("/treasures/get")
    public ArrayList<Treasure> getTreasures() {
        return tableService.getTreasures();
    }
}
