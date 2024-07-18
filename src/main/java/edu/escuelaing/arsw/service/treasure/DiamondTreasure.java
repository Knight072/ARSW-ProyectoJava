package edu.escuelaing.arsw.service.treasure;

import edu.escuelaing.arsw.service.treasure.Treasure;

public class DiamondTreasure extends Treasure {
    public DiamondTreasure() {
        this.score = 300;
        this.positionX = rand.nextInt(9);
        this.positionY = rand.nextInt(9);
        while (table.getTable()[positionX][positionY] != 0 && table.getTable()[positionX][positionY] != 2 && table.getTable()[positionX][positionY] != 3) {
            this.positionX = rand.nextInt(9);
            this.positionY = rand.nextInt(9);
        }
        table.getTable()[positionX][positionY] = number;
    }
}
