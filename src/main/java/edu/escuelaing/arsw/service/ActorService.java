package edu.escuelaing.arsw.service;

import edu.escuelaing.arsw.service.actor.Actor;
import edu.escuelaing.arsw.service.factory.ActorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private ActorFactory actorFactory;

    @Autowired
    public ActorService(ActorFactory actorFactory) {
        this.actorFactory = actorFactory;
    }

    public Actor getActor(String id) {
        return Actor.getPlayers().get(id);
    }

    public Actor createActor(Integer tipoActor) {
        return actorFactory.createActor(tipoActor);
    }

    public void moveActor(Integer positionX, Integer positionY, String id) {
        Actor.getPlayers().get(id).move(positionX, positionY);
    }
}
