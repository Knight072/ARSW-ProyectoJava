package edu.escuelaing.arsw.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        CustomConfigurator.setApplicationContext(applicationContext);
        WebSocketConfig.applicationContext = applicationContext;
    }
}

