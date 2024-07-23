package edu.escuelaing.arsw.service;

import edu.escuelaing.arsw.service.treasure.Treasure;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TableService {
    private Table table;

    public TableService() {
        this.table = Table.getInstance();
    }

    public Integer[][] getTable() {
        table.createTreasure();
        return table.getTable();
    }

    public void createTreasures() {
        table.createTreasure();
    }

    public HashMap<String, Treasure> getTreasures() {
        return table.getTreasure();
    }
}
