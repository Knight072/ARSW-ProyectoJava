package edu.escuelaing.arsw;

import java.util.HashMap;

public abstract class Actor {
    protected Table table;
    protected Integer positionX;
    protected Integer positionY;
    protected Integer number;
    protected static HashMap<Integer, Actor> players;


    public void move() {
    }

    public static HashMap<Integer, Actor> getPlayers() {
        if (players == null) {
            return players = new HashMap<>();
        }
        return players;
    }

}
