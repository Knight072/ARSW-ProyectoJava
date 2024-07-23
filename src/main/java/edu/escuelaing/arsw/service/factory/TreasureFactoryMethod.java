package edu.escuelaing.arsw.service.factory;

/**
 * The TreasureFactoryMethod interface defines a contract for creating treasures.
 * Implementing classes must provide an implementation for creating treasures.
 */
public interface TreasureFactoryMethod {

        /**
         * Creates treasures according to the specific implementation.
         * This method should be implemented to instantiate and manage treasure objects.
         */
        void createTreasure();
}

