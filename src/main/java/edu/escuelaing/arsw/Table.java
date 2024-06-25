package edu.escuelaing.arsw;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Table {

    private static Table INSTANCE;
    private Integer[][] table = new Integer[10][10];
    private HashMap<Integer, Actor> players = new HashMap<>();
    private final Random rand = new Random();

    private Table() {
        generarLaberinto();
    }

    public static Table getInstance() {
        if (INSTANCE == null) {
            return INSTANCE = new Table();
        }
        return INSTANCE;
    }

    private void generarLaberinto() {
        // Inicializar todas las celdas como paredes
        for (int i = 0; i < table.length; i++) {
            Arrays.fill(table[i], 1);
        }

        // Crear el laberinto usando Recursive Backtracking
        crearCamino(1, 1);

        // Cerrar la salida (hacerlo cerrado)
        table[table.length - 2][table[0].length - 2] = 1;

        // Crear caminos adicionales
        agregarCaminosAdicionales();
    }

    private void crearCamino(int x, int y) {
        table[x][y] = 0; // Marca la celda como parte del camino

        // Direcciónes en el orden N, S, E, O
        Integer[] direcciones = {0, 1, 2, 3};
        Collections.shuffle(Arrays.asList(direcciones), rand);

        for (int i = 0; i < direcciones.length; i++) {
            switch (direcciones[i]) {
                case 0: // Norte
                    if (x - 2 > 0 && table[x - 2][y] == 1) {
                        table[x - 2][y] = 0;
                        table[x - 1][y] = 0;
                        crearCamino(x - 2, y);
                    }
                    break;
                case 1: // Sur
                    if (x + 2 < table.length - 1 && table[x + 2][y] == 1) {
                        table[x + 2][y] = 0;
                        table[x + 1][y] = 0;
                        crearCamino(x + 2, y);
                    }
                    break;
                case 2: // Este
                    if (y + 2 < table[0].length - 1 && table[x][y + 2] == 1) {
                        table[x][y + 2] = 0;
                        table[x][y + 1] = 0;
                        crearCamino(x, y + 2);
                    }
                    break;
                case 3: // Oeste
                    if (y - 2 > 0 && table[x][y - 2] == 1) {
                        table[x][y - 2] = 0;
                        table[x][y - 1] = 0;
                        crearCamino(x, y - 2);
                    }
                    break;
            }
        }
    }

    private void agregarCaminosAdicionales() {
        for (int i = 1; i < table.length - 1; i++) {
            for (int j = 1; j < table[0].length - 1; j++) {
                if (table[i][j] == 1 && rand.nextDouble() < 0.3) { // Probabilidad de 30% de convertir una pared en camino
                    table[i][j] = 0;
                }
            }
        }
    }

    public void print() {
        for (Integer[] fila : table) {
            for (Integer celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }

    public void createThief(){
        players.put(1, new Thief());
    }

    public Integer[][] getTable() {
        return table;
    }


    public int getCasilla(int positionX, int positionY) {
        return Table.getInstance().getTable()[positionX][positionY];
    }

}

