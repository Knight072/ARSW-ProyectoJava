package edu.escuelaing.arsw;

public class GoldTreasure extends Treasure{
    public GoldTreasure() {
        this.score = 200;
        this.positionX = rand.nextInt(9);
        this.positionY = rand.nextInt(9);
        while (table.getTable()[positionX][positionY] != 0 && table.getTable()[positionX][positionY] != 2 && table.getTable()[positionX][positionY] != 3) {
            this.positionX = rand.nextInt(9);
            this.positionY = rand.nextInt(9);
        }
        table.getTable()[positionX][positionY] = number;
    }
}
