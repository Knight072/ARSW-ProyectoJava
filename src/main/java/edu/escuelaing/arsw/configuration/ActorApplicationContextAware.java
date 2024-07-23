package edu.escuelaing.arsw.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * The ActorApplicationContextAware class is a Spring component that implements
 * the ApplicationContextAware interface. This allows the class to be aware of
 * the Spring application context and provides a way to access it statically
 * from anywhere in the application.
 *
 * <p>This class is marked with the @Component annotation to indicate that it is
 * a Spring-managed component. The @Lazy(false) annotation ensures that this
 * bean is initialized eagerly at startup.
 */
@Component
@Lazy(false)
public class ActorApplicationContextAware implements ApplicationContextAware {

    /**
     * A static reference to the Spring application context.
     */
    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * Sets the application context. This method is called by the Spring
     * framework to inject the application context into this class.
     *
     * @param applicationContext the application context
     * @throws BeansException if an error occurs while setting the application context
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * Returns the Spring application context.
     *
     * @return the application context
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }
}
