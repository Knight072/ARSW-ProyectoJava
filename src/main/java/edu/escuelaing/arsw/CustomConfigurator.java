package edu.escuelaing.arsw;

import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.context.ApplicationContext;

public class CustomConfigurator extends ServerEndpointConfig.Configurator {
    private static ApplicationContext applicationContext;

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return applicationContext.getAutowireCapableBeanFactory().createBean(endpointClass);
    }

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }
}

