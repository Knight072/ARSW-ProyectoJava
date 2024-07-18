package edu.escuelaing.arsw.service.actor;

import edu.escuelaing.arsw.Table;

import java.util.HashMap;


public abstract class Actor {
    protected Table table;
    protected String id;
    protected Integer positionX;
    protected Integer positionY;
    protected Integer number;
    protected static HashMap<String, Actor> players;


    public void move(Integer newPositionX, Integer newPositionY) {
    }

    public static HashMap<String, Actor> getPlayers() {
        if (players == null) {
            return players = new HashMap<>();
        }
        return players;
    }

    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }

    public String getId() {
        return id;
    }
}
