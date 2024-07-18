package edu.escuelaing.arsw.service.treasure;

import edu.escuelaing.arsw.Table;

import java.util.Random;

public class Treasure {
    protected Table table = Table.getInstance();
    protected Integer number = 4;
    protected Integer score;
    protected Integer positionX;
    protected Integer positionY;
    protected Random rand = new Random();

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public Integer getScore() {
        return score;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }
}
