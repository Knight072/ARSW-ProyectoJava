package edu.escuelaing.arsw.service.factory;

import edu.escuelaing.arsw.service.treasure.GoldTreasure;
import edu.escuelaing.arsw.service.treasure.SilverTreasure;
import edu.escuelaing.arsw.service.treasure.DiamondTreasure;
import edu.escuelaing.arsw.service.treasure.Treasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreasureFactoryTest {

    private TreasureFactory treasureFactory;
    private final int amount = 10;

    @BeforeEach
    void setUp() {
        treasureFactory = new TreasureFactory(amount);
    }

    @Test
    void testTreasureCreation() {
        HashMap<String, Treasure> treasures = treasureFactory.getTreasures();
        assertEquals(amount, treasures.size(), "The number of treasures created should be equal to the amount specified.");

        // Verify that each treasure has a unique position and valid type
        for (Treasure treasure : treasures.values()) {
            assertTrue(treasure instanceof GoldTreasure || treasure instanceof SilverTreasure || treasure instanceof DiamondTreasure,
                    "Each treasure should be an instance of GoldTreasure, SilverTreasure, or DiamondTreasure.");

            String positionKey = Integer.toString(treasure.getPositionX()) + Integer.toString(treasure.getPositionY());
            assertTrue(treasures.containsKey(positionKey), "The position key should exist in the treasures map.");
        }
    }
}

