package edu.escuelaing.arsw.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * The WebSocketConfig class is a configuration class for setting up WebSocket
 * support in the application. It implements ApplicationContextAware to gain
 * access to the Spring application context.
 *
 * <p>It is marked with the @Configuration annotation to indicate that it is a Spring
 * configuration class.
 */
@Configuration
public class WebSocketConfig implements ApplicationContextAware {

    /**
     * A static reference to the Spring application context.
     */
    private static ApplicationContext applicationContext;

    /**
     * Creates and returns a ServerEndpointExporter bean. This bean will automatically
     * register any beans annotated with @ServerEndpoint.
     *
     * @return the ServerEndpointExporter bean
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * Sets the application context. This method is called by the Spring framework to
     * inject the application context into this class. It also sets the application
     * context in the CustomConfigurator class.
     *
     * @param applicationContext the application context
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        CustomConfigurator.setApplicationContext(applicationContext);
        WebSocketConfig.applicationContext = applicationContext;
    }
}


