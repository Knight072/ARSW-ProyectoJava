package edu.escuelaing.arsw.service.treasure;

import edu.escuelaing.arsw.service.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TreasureTest {

    private Treasure treasure;
    private Table mockTable;

    @BeforeEach
    void setUp() {
        // Crear una subclase concreta de Treasure para pruebas
        treasure = new Treasure() {
            {
                score = 100;
                positionX = 5;
                positionY = 10;
            }
        };

        // Mockear Table para evitar dependencias externas
        mockTable = mock(Table.class);
        treasure.table = mockTable;
    }

    @Test
    void testGetScore() {
        assertEquals(100, treasure.getScore());
    }

    @Test
    void testGetPositionX() {
        assertEquals(5, treasure.getPositionX());
    }

    @Test
    void testGetPositionY() {
        assertEquals(10, treasure.getPositionY());
    }

    @Test
    void testTableInitialization() {
        assertNotNull(treasure.table, "Table should be initialized");
    }

    @Test
    void testRandomInitialization() {
        assertNotNull(treasure.rand, "Random should be initialized");
    }

    @Test
    void testNumberInitialization() {
        assertEquals(4, treasure.number, "Number should be initialized to 4");
    }

    @Test
    void testPlayersInitialization() {
        assertNull(Treasure.players, "Players should be initially null");
    }
}
