package edu.escuelaing.arsw;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ActorFactory implements ActorFactoryMethod {

    private Random rand = new Random();

    public void createActor(int tipoActor) {
        if (tipoActor == 3) {
            Thief thief = new Thief();
            thief.getPlayers().put(rand.nextInt(), thief);
            System.out.println(thief.getPlayers());
        } else if (tipoActor == 2) {

        }
    }
}
