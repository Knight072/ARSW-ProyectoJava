package edu.escuelaing.arsw.service;

import edu.escuelaing.arsw.service.factory.TreasureFactory;
import edu.escuelaing.arsw.service.treasure.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {

    private Table table;

    @BeforeEach
    public void setUp() {
        table = Table.getInstance();
    }

    @Test
    public void testSingletonInstance() {
        Table anotherTable = Table.getInstance();
        assertSame(table, anotherTable, "Debe retornar la misma instancia de Table");
    }

    @Test
    public void testGenerateLaberinto() {
        Integer[][] laberinto = table.getTable();
        assertNotNull(laberinto, "El laberinto no debe ser null");
        assertEquals(10, laberinto.length, "El laberinto debe tener 10 filas");
        assertEquals(20, laberinto[0].length, "El laberinto debe tener 20 columnas");
    }

    @Test
    public void testCreateTreasure() {
        table.createTreasure();
        HashMap<String, Treasure> treasures = table.getTreasure();
        assertNotNull(treasures, "El mapa de tesoros no debe ser null");
        assertEquals(4, treasures.size(), "Debe haber 4 tesoros creados");
    }
}

