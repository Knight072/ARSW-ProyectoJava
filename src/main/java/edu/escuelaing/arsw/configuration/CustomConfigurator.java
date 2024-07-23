package edu.escuelaing.arsw.configuration;

import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.context.ApplicationContext;

/**
 * The CustomConfigurator class is a custom configuration class that extends
 * ServerEndpointConfig.Configurator. This class allows the use of Spring's
 * dependency injection in WebSocket endpoints by retrieving the necessary beans
 * from the Spring application context.
 */
public class CustomConfigurator extends ServerEndpointConfig.Configurator {

    /**
     * A static reference to the Spring application context.
     */
    private static ApplicationContext applicationContext;

    /**
     * Returns an instance of the specified endpoint class. This method is overridden
     * to create the endpoint instances using Spring's autowiring capabilities.
     *
     * @param endpointClass the class of the endpoint
     * @param <T> the type of the endpoint
     * @return an instance of the endpoint class with dependencies injected
     * @throws InstantiationException if an instance of the endpoint class cannot be created
     */
    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return applicationContext.getAutowireCapableBeanFactory().createBean(endpointClass);
    }

    /**
     * Sets the Spring application context. This method is used to inject the
     * application context into this configurator.
     *
     * @param context the application context
     */
    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }
}


