package edu.escuelaing.arsw;

import edu.escuelaing.arsw.service.actor.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ThiefTest {

    private Table table;
    private Thief thief;

    @BeforeEach
    public void setUp() {
        table = Table.getInstance();
        String uniqueId = UUID.randomUUID().toString(); // Generar un UUID único
        thief = new Thief(uniqueId);
    }

    @Test
    public void testThiefCreation() {
        assertNotNull(thief); // Verificar que el ladrón no sea nulo
        assertTrue(thief.getPositionX() >= 0 && thief.getPositionX() < 9); // Verificar que la posición X esté en el rango esperado
        assertTrue(thief.getPositionY() >= 0 && thief.getPositionY() < 9); // Verificar que la posición Y esté en el rango esperado
        assertEquals(3, table.getTable()[thief.getPositionX()][thief.getPositionY()]); // Verificar que la posición del ladrón en la tabla sea correcta
        assertNotNull(thief.getId()); // Verificar que el ladrón tenga un ID asignado
        assertEquals(thief.getId(), thief.getId()); // Verificar que el ID sea el mismo que se asignó
    }

    @Test
    public void testThiefMovement() {
        int oldPositionX = thief.getPositionX();
        int oldPositionY = thief.getPositionY();

        thief.move(2, 2); // Mover el ladrón a una nueva posición válida

        assertEquals(0, table.getTable()[oldPositionX][oldPositionY]); // Verificar que la antigua posición esté vacía
        assertEquals(3, table.getTable()[2][2]); // Verificar que la nueva posición contenga al ladrón
        assertEquals(2, thief.getPositionX()); // Verificar que la posición X del ladrón se haya actualizado
        assertEquals(2, thief.getPositionY()); // Verificar que la posición Y del ladrón se haya actualizado
    }

    @Test
    public void testThiefInvalidMovement() {
        int oldPositionX = thief.getPositionX();
        int oldPositionY = thief.getPositionY();

        table.getTable()[1][1] = 1; // Establecer una posición inválida (muro)
        thief.move(1, 1); // Intentar mover el ladrón a la posición inválida

        assertEquals(3, table.getTable()[oldPositionX][oldPositionY]); // Verificar que la antigua posición aún contenga al ladrón
        assertEquals(1, table.getTable()[1][1]); // Verificar que la posición inválida no haya cambiado
        assertEquals(oldPositionX, thief.getPositionX()); // Verificar que la posición X del ladrón no haya cambiado
        assertEquals(oldPositionY, thief.getPositionY()); // Verificar que la posición Y del ladrón no haya cambiado
    }
}


