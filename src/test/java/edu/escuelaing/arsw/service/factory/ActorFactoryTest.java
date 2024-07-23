package edu.escuelaing.arsw.service.factory;

import edu.escuelaing.arsw.service.actor.Actor;
import edu.escuelaing.arsw.service.actor.Police;
import edu.escuelaing.arsw.service.actor.Thief;
import edu.escuelaing.arsw.service.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActorFactoryTest {

    private ActorFactory actorFactory;
    private Table table;

    @BeforeEach
    public void setUp() {
        actorFactory = new ActorFactory();
        table = Table.getInstance();
    }

    @Test
    public void testCreateThief() {
        Actor thief = actorFactory.createActor(3);
        assertNotNull(thief, "El ladrón no debe ser null");
        assertTrue(thief instanceof Thief, "El actor debe ser una instancia de Thief");
        assertTrue(Actor.getPlayers().containsValue(thief), "El ladrón debe estar en la lista de jugadores");
    }

    @Test
    public void testCreatePolice() {
        Actor police = actorFactory.createActor(2);
        assertNotNull(police, "El policía no debe ser null");
        assertTrue(police instanceof Police, "El actor debe ser una instancia de Police");
        assertTrue(Actor.getPlayers().containsValue(police), "El policía debe estar en la lista de jugadores");
    }
}


