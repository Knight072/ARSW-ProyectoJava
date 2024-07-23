package edu.escuelaing.arsw.service.actor;

import edu.escuelaing.arsw.service.Table;
import edu.escuelaing.arsw.service.treasure.SilverTreasure;
import edu.escuelaing.arsw.service.treasure.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThiefTest {

    private Thief thief;
    private Table table;

    @BeforeEach
    public void setUp() {
        table = Table.getInstance();
        table.createTreasure();
        thief = new Thief("1");
    }

    @Test
    public void testThiefInitialization() {
        assertNotNull(thief.getId(), "El ID del ladrón no debe ser null");
        assertEquals(3, thief.getNumber(), "El número del ladrón debe ser 3");
        assertTrue(thief.getPositionX() >= 0 && thief.getPositionX() < 10, "La posición X debe estar en el rango 0-9");
        assertTrue(thief.getPositionY() >= 0 && thief.getPositionY() < 10, "La posición Y debe estar en el rango 0-9");
        assertEquals(3, table.getTable()[thief.getPositionX()][thief.getPositionY()], "La posición inicial del ladrón debe estar marcada en la tabla");
    }

    @Test
    public void testMoveToEmptyPosition() {
        int initialX = thief.getPositionX();
        int initialY = thief.getPositionY();
        int newX = (initialX + 1) % 10; // Nueva posición en el rango
        int newY = (initialY + 1) % 10; // Nueva posición en el rango

        thief.move(newX, newY);

        assertEquals(newX, thief.getPositionX(), "La nueva posición X debe ser " + newX);
        assertEquals(newY, thief.getPositionY(), "La nueva posición Y debe ser " + newY);
        assertEquals(0, table.getTable()[initialX][initialY], "La posición anterior debe estar vacía");
        assertEquals(3, table.getTable()[newX][newY], "La nueva posición debe estar ocupada por el ladrón");
    }

    @Test
    public void testMoveToTreasurePosition() {
        // Colocar un tesoro en una posición específica
        int treasureX = 5;
        int treasureY = 5;
        Treasure treasure = new SilverTreasure();
        table.getTreasure().put(treasureX + Integer.toString(treasureY), treasure);
        table.getTable()[treasureX][treasureY] = 4; // Asumiendo que 4 representa la posición del tesoro

        thief.move(treasureX, treasureY);

        assertEquals(treasureX, thief.getPositionX(), "La nueva posición X debe ser " + treasureX);
        assertEquals(treasureY, thief.getPositionY(), "La nueva posición Y debe ser " + treasureY);
        assertNull(table.getTreasure().get(treasureX + Integer.toString(treasureY)), "El tesoro debe ser removido de la tabla");
        assertEquals(3, table.getTable()[treasureX][treasureY], "La nueva posición debe estar ocupada por el ladrón");
    }
}

