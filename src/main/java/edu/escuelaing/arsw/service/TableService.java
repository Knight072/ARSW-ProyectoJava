package edu.escuelaing.arsw.service;

import edu.escuelaing.arsw.Table;
import edu.escuelaing.arsw.service.treasure.Treasure;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    public ArrayList<Treasure> getTreasures() {
        return table.getTreasure();
    }
}
