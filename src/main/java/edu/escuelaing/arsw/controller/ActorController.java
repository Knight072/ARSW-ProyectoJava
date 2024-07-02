package edu.escuelaing.arsw.controller;

import edu.escuelaing.arsw.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ActorController {

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @RequestMapping("/actor/{tipo}")
    public void createActor(@PathVariable("tipo") Integer tipo) {
        actorService.createActor(tipo);
    }
}
