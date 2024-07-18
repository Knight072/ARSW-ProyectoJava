package edu.escuelaing.arsw.controller;

import edu.escuelaing.arsw.service.ActorService;
import edu.escuelaing.arsw.service.actor.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@CrossOrigin("*")
public class ActorController {

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actor/get/{id}")
    public ResponseEntity<Actor> getActor(@PathVariable("id") String id) {
        return new ResponseEntity<>(actorService.getActor(id), HttpStatus.OK);
    }

    @GetMapping("/actor/{tipo}")
    public ResponseEntity<Actor> createActor(@PathVariable("tipo") Integer tipo) {
        return new ResponseEntity<>(actorService.createActor(tipo), HttpStatus.OK);
    }

    @PostMapping("/actor/move/{x}/{y}/{id}")
    public void moveActor(@PathVariable("x") Integer positionX, @PathVariable("y") Integer positionY, @PathVariable("id") String id) {
        actorService.moveActor(positionX, positionY, id);
    }

}
