package edu.escuelaing.arsw.service;

import edu.escuelaing.arsw.ActorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private ActorFactory actorFactory;

    @Autowired
    public ActorService(ActorFactory actorFactory) {
        this.actorFactory = actorFactory;
    }

    public void createActor(Integer tipoActor) {
        actorFactory.createActor(tipoActor);
    }
}
