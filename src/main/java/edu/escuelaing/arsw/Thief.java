package edu.escuelaing.arsw;

import java.util.Random;

public class Thief extends Actor {
    private final Random rand = new Random();

    public Thief() {
        this.number = 3;
        this.table = Table.getInstance();
        this.positionX = rand.nextInt(9);
        this.positionY = rand.nextInt(9);
        while (table.getTable()[positionX][positionY] != 0 && table.getTable()[positionX][positionY] != 2) {
            this.positionX = rand.nextInt(9);
            this.positionY = rand.nextInt(9);
        }
        table.getTable()[positionX][positionY] = number;
    }

    @Override
    public void move() {

    }

    public Table getTable() {
        return table;
    }

}
