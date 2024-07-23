package edu.escuelaing.arsw.service.factory;

import edu.escuelaing.arsw.service.treasure.Treasure;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TreasureFactory {
    private HashMap<String, Treasure> treasures = new HashMap<>();
    private ArrayList<String> typeTreasure = new ArrayList<>();
    private Random rand = new Random();
    private Integer amount;

    public TreasureFactory(Integer amount) {
        this.amount = amount;
        // Inicializar la lista de tipos de tesoro
        typeTreasure.add("edu.escuelaing.arsw.service.treasure.GoldTreasure");
        typeTreasure.add("edu.escuelaing.arsw.service.treasure.SilverTreasure");
        typeTreasure.add("edu.escuelaing.arsw.service.treasure.DiamondTreasure");

        for (int index = 0; index < amount; index++) {
            String type = typeTreasure.get(rand.nextInt(typeTreasure.size()));
            try {
                // Obtener la clase a partir de su nombre
                Class<?> clase = Class.forName(type);
                Treasure treasure = (Treasure) clase.getDeclaredConstructor().newInstance();
                // Crear una nueva instancia de la clase
                treasures.put(Integer.toString( treasure.getPositionX()) + Integer.toString(treasure.getPositionY()),treasure);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException("Error al crear el producto: " + type, e);
            }
        }
    }

    public HashMap<String, Treasure> getTreasures(){
        return treasures;
    }
}
