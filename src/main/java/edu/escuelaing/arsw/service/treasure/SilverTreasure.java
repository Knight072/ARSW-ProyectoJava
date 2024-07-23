package edu.escuelaing.arsw.service.treasure;

public class SilverTreasure extends Treasure {
    public SilverTreasure() {
        this.score = 100;
        do {
            this.positionX = rand.nextInt(9);
            this.positionY = rand.nextInt(19);
        } while (table.getTable()[positionX][positionY] != 0 && table.getTable()[positionX][positionY] != 2 && table.getTable()[positionX][positionY] != 3);
        table.getTable()[positionX][positionY] = number;
    }
}
