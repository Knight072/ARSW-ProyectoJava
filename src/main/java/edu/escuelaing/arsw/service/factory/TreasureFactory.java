package edu.escuelaing.arsw.service.factory;

import edu.escuelaing.arsw.service.treasure.Treasure;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The TreasureFactory class is responsible for creating instances of different types of treasures.
 * It uses reflection to dynamically instantiate treasure objects and stores them in a map.
 */
public class TreasureFactory {

    /**
     * A map storing created treasures, where the key is a combination of the treasure's position coordinates.
     */
    private HashMap<String, Treasure> treasures = new HashMap<>();

    /**
     * A list of fully qualified class names of different types of treasures.
     */
    private ArrayList<String> typeTreasure = new ArrayList<>();

    /**
     * Random number generator used for selecting treasure types and positions.
     */
    private Random rand = new Random();

    /**
     * The number of treasures to create.
     */
    private Integer amount;

    /**
     * Constructs a TreasureFactory instance with the specified number of treasures to create.
     * Initializes the list of treasure types and creates the specified number of treasure instances.
     *
     * @param amount the number of treasures to create
     */
    public TreasureFactory(Integer amount) {
        this.amount = amount;

        // Initialize the list of treasure types
        typeTreasure.add("edu.escuelaing.arsw.service.treasure.GoldTreasure");
        typeTreasure.add("edu.escuelaing.arsw.service.treasure.SilverTreasure");
        typeTreasure.add("edu.escuelaing.arsw.service.treasure.DiamondTreasure");

        // Create treasures
        for (int index = 0; index < amount; index++) {
            String type = typeTreasure.get(rand.nextInt(typeTreasure.size()));
            try {
                // Obtain the class object from the class name
                Class<?> clazz = Class.forName(type);
                // Create a new instance of the class
                Treasure treasure = (Treasure) clazz.getDeclaredConstructor().newInstance();
                // Store the treasure in the map with its position as the key
                treasures.put(Integer.toString(treasure.getPositionX()) + Integer.toString(treasure.getPositionY()), treasure);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException("Error creating treasure: " + type, e);
            }
        }
    }

    /**
     * Returns a map of created treasures.
     *
     * @return a map where the key is a combination of the treasure's position coordinates
     * and the value is the corresponding treasure object
     */
    public HashMap<String, Treasure> getTreasures() {
        return treasures;
    }
}

