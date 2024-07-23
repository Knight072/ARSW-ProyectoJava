package edu.escuelaing.arsw.service.actor;

import edu.escuelaing.arsw.service.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TestActor extends Actor {
    public TestActor(String id, Integer number, Table table, Integer positionX, Integer positionY) {
        this.id = id;
        this.number = number;
        this.table = table;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    public void move(Integer newPositionX, Integer newPositionY) {
        this.positionX = newPositionX;
        this.positionY = newPositionY;
    }
}

public class ActorTest {

    private TestActor actor;
    private Table table;

    @BeforeEach
    public void setUp() {
        table = Table.getInstance();  // Usar el singleton Table.getInstance()
        actor = new TestActor("1", 1, table, 0, 0);
    }

    @Test
    public void testMove() {
        actor.move(5, 5);
        assertEquals(5, actor.getPositionX(), "La posición X debe ser 5");
        assertEquals(5, actor.getPositionY(), "La posición Y debe ser 5");
    }

    @Test
    public void testGetPlayers() {
        HashMap<String, Actor> players = Actor.getPlayers();
        assertNotNull(players, "La lista de jugadores no debe ser null");
    }

    @Test
    public void testGetNumber() {
        assertEquals(1, actor.getNumber(), "El número del actor debe ser 1");
    }

    @Test
    public void testGetPositionX() {
        assertEquals(0, actor.getPositionX(), "La posición X inicial debe ser 0");
    }

    @Test
    public void testGetPositionY() {
        assertEquals(0, actor.getPositionY(), "La posición Y inicial debe ser 0");
    }

    @Test
    public void testGetId() {
        assertEquals("1", actor.getId(), "El ID del actor debe ser '1'");
    }
}


