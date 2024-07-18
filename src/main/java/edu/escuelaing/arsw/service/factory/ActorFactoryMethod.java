package edu.escuelaing.arsw.service.factory;

import edu.escuelaing.arsw.service.actor.Actor;

public interface ActorFactoryMethod {
    Actor createActor(int tipoActor);
}
