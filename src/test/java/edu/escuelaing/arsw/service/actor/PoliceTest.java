package edu.escuelaing.arsw.service.actor;

import edu.escuelaing.arsw.service.Table;
import edu.escuelaing.arsw.service.treasure.Treasure;
import edu.escuelaing.arsw.service.treasure.SilverTreasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PoliceTest {

    private Police police;
    private Table table;

    @BeforeEach
    public void setUp() {
        table = Table.getInstance();
        table.createTreasure();
        police = new Police("1");
    }

    @Test
    public void testPoliceInitialization() {
        assertNotNull(police.getId(), "El ID del policía no debe ser null");
        assertEquals(2, police.getNumber(), "El número del policía debe ser 2");
        assertTrue(police.getPositionX() >= 0 && police.getPositionX() < 10, "La posición X debe estar en el rango 0-9");
        assertTrue(police.getPositionY() >= 0 && police.getPositionY() < 20, "La posición Y debe estar en el rango 0-19");
        assertEquals(2, table.getTable()[police.getPositionX()][police.getPositionY()], "La posición inicial del policía debe estar marcada en la tabla");
    }

    @Test
    public void testMoveToEmptyPosition() {
        int initialX = police.getPositionX();
        int initialY = police.getPositionY();
        int newX = (initialX + 1) % 10; // Nueva posición en el rango
        int newY = (initialY + 1) % 20; // Nueva posición en el rango

        police.move(newX, newY);

        assertEquals(newX, police.getPositionX(), "La nueva posición X debe ser " + newX);
        assertEquals(newY, police.getPositionY(), "La nueva posición Y debe ser " + newY);
        assertEquals(0, table.getTable()[initialX][initialY], "La posición anterior debe estar vacía");
        assertEquals(2, table.getTable()[newX][newY], "La nueva posición debe estar ocupada por el policía");
    }

    @Test
    public void testMoveToTreasurePosition() {
        // Colocar un tesoro en una posición específica
        int treasureX = 5;
        int treasureY = 5;
        Treasure treasure = new SilverTreasure();
        table.getTreasure().put(treasureX + Integer.toString(treasureY), treasure);
        table.getTable()[treasureX][treasureY] = 4; // Asumiendo que 4 representa la posición del tesoro

        police.move(treasureX, treasureY);

        assertEquals(treasureX, police.getPositionX(), "La nueva posición X debe ser " + treasureX);
        assertEquals(treasureY, police.getPositionY(), "La nueva posición Y debe ser " + treasureY);
        assertNull(table.getTreasure().get(treasureX + Integer.toString(treasureY)), "El tesoro debe ser removido de la tabla");
        assertEquals(2, table.getTable()[treasureX][treasureY], "La nueva posición debe estar ocupada por el policía");
    }
}

