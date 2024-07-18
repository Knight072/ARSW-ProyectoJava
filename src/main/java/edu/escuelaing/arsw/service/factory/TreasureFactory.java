package edu.escuelaing.arsw.service.factory;

import edu.escuelaing.arsw.service.treasure.Treasure;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class TreasureFactory {
    private ArrayList<Treasure> treasure = new ArrayList<>();
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
                // Crear una nueva instancia de la clase
                treasure.add((Treasure) clase.getDeclaredConstructor().newInstance());
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException("Error al crear el producto: " + type, e);
            }
        }
    }

    public ArrayList<Treasure> getTreasures(){
        return treasure;
    }

    // Método para obtener un tesoro por su índice
    public Treasure getTreasure(Integer index) {
        return treasure.get(index);
    }

    // Método para agregar un nuevo tipo de tesoro
    public void addTypeTreasure(String type) {
        typeTreasure.add(type);
    }
}
