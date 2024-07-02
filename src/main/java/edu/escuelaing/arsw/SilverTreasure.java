package edu.escuelaing.arsw;

public class SilverTreasure extends Treasure{
    public SilverTreasure() {
        this.score = 100;
        this.positionX = rand.nextInt(9);
        this.positionY = rand.nextInt(9);
        while (table.getTable()[positionX][positionY] != 0 && table.getTable()[positionX][positionY] != 2 && table.getTable()[positionX][positionY] != 3) {
            this.positionX = rand.nextInt(9);
            this.positionY = rand.nextInt(9);
        }
        table.getTable()[positionX][positionY] = number;
    }
}
