package edu.escuelaing.arsw.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The MvcConfig class is a configuration class that implements the WebMvcConfigurer
 * interface. This class is used to configure Spring MVC by adding view controllers
 * to the application.
 *
 * <p>It is marked with the @Configuration annotation to indicate that it is a
 * Spring configuration class.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Adds view controllers to the registry. This method is used to map URLs to
     * specific view names without the need for a controller.
     *
     * @param registry the ViewControllerRegistry to add the view controllers to
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home.html");
        registry.addViewController("/hello").setViewName("hello.html");
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/static/index").setViewName("index.html");
    }
}

