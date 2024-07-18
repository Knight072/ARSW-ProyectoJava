package edu.escuelaing.arsw.service.factory;

import edu.escuelaing.arsw.service.actor.Actor;
import edu.escuelaing.arsw.service.actor.Police;
import edu.escuelaing.arsw.service.actor.Thief;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class ActorFactory implements ActorFactoryMethod {

    public Actor createActor(int tipoActor) {
        String id = UUID.randomUUID().toString();
        if (tipoActor == 3) {
            Actor thief = new Thief(id);
            thief.getPlayers().put(id, thief);
        } else if (tipoActor == 2) {
            Actor police = new Police(id);
        }
        return Actor.getPlayers().get(id);
    }
}
