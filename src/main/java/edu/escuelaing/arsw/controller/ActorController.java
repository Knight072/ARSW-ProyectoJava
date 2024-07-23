package edu.escuelaing.arsw.controller;

import edu.escuelaing.arsw.service.ActorService;
import edu.escuelaing.arsw.service.actor.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

/**
 * The ActorController class is a REST controller that handles HTTP requests related
 * to actors in the application. It provides endpoints for retrieving, creating, and
 * moving actors.
 *
 * <p>It is marked with the @RestController annotation to indicate that it is a Spring
 * REST controller, and @CrossOrigin to allow cross-origin requests.
 */
@RestController
@CrossOrigin("*")
public class ActorController {

    /**
     * The service used to manage actors.
     */
    private final ActorService actorService;

    /**
     * Constructs an ActorController with the specified ActorService.
     *
     * @param actorService the service used to manage actors
     */
    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    /**
     * Retrieves an actor by its ID.
     *
     * @param id the ID of the actor to retrieve
     * @return a ResponseEntity containing the retrieved actor and an HTTP status of OK
     */
    @GetMapping("/actor/get/{id}")
    public ResponseEntity<Actor> getActor(@PathVariable("id") String id) {
        return new ResponseEntity<>(actorService.getActor(id), HttpStatus.OK);
    }

    /**
     * Creates a new actor of the specified type.
     *
     * @param tipo the type of actor to create
     * @return a ResponseEntity containing the created actor and an HTTP status of OK
     */
    @GetMapping("/actor/{tipo}")
    public ResponseEntity<Actor> createActor(@PathVariable("tipo") Integer tipo) {
        return new ResponseEntity<>(actorService.createActor(tipo), HttpStatus.OK);
    }

    /**
     * Moves an actor to the specified position.
     *
     * @param positionX the new X position of the actor
     * @param positionY the new Y position of the actor
     * @param id the ID of the actor to move
     */
    @PostMapping("/actor/move/{x}/{y}/{id}")
    public void moveActor(@PathVariable("x") Integer positionX, @PathVariable("y") Integer positionY, @PathVariable("id") String id) {
        actorService.moveActor(positionX, positionY, id);
    }
}

