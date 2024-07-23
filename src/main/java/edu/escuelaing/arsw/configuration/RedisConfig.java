package edu.escuelaing.arsw.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * The RedisConfig class is a configuration class for setting up the connection
 * to a Redis data store. This class uses properties defined in the
 * application.properties file to configure the Redis connection.
 *
 * <p>It is marked with the @Configuration annotation to indicate that it is a
 * Spring configuration class, and @PropertySource to specify the properties file
 * from which to load the Redis configuration properties.
 */
@Configuration
@PropertySource("application.properties")
public class RedisConfig {

    /**
     * The hostname of the Redis server, injected from the application.properties file.
     */
    @Value("${redis.bbcache.hostname}")
    private String redisHostName;

    /**
     * The port number of the Redis server, injected from the application.properties file.
     */
    @Value("${redis.bbcache.port}")
    private int redisPort;

    /**
     * Creates and returns a LettuceConnectionFactory for connecting to the Redis server.
     * This factory uses the Redis standalone configuration with the specified hostname
     * and port.
     *
     * @return a LettuceConnectionFactory configured for the Redis server
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHostName, redisPort));
    }
}

