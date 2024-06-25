package edu.escuelaing.arsw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TableController {
    public Table table;
    public TableController(){
        this.table = Table.getInstance();
    }

    @GetMapping("/table")
    public Integer[][] getTable(){
        return table.getTable();
    }
}
