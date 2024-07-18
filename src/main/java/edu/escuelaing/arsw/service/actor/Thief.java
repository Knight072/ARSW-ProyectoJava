package edu.escuelaing.arsw.service.actor;

import edu.escuelaing.arsw.Table;

import java.util.Random;

public class Thief extends Actor {
    private final Random rand = new Random();

    public Thief(String id) {
        this.id = id;
        this.number = 3;
        this.table = Table.getInstance();
        do {
            this.positionX = rand.nextInt(9);
            this.positionY = rand.nextInt(9);
        } while (table.getTable()[positionX][positionY] != 0 && table.getTable()[positionX][positionY] != 2);
        table.getTable()[positionX][positionY] = number;
    }

    @Override
    public void move(Integer newPositionX, Integer newPositionY) {
        if (this.table.getTable()[newPositionX][newPositionY] != 1) {
            this.table.getTable()[positionX][positionY] = 0;
            this.positionX = newPositionX;
            this.positionY = newPositionY;
            this.table.getTable()[positionX][positionY] = number;
        }
    }
}
