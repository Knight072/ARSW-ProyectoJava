package edu.escuelaing.arsw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The WebSecurityConfig class is a configuration class for setting up Spring Security
 * in the application. This class defines the security configuration, including HTTP
 * security rules, login, and logout configurations, as well as user details service
 * for authentication.
 *
 * <p>It is marked with the @Configuration annotation to indicate that it is a Spring
 * configuration class, and @EnableWebSecurity to enable web security support in Spring.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Configures the security filter chain. This method sets up HTTP security rules,
     * allowing access to the root ("/") and "/home.html" without authentication,
     * while all other requests require authentication. It also configures form-based
     * login and logout functionality.
     *
     * @param http the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs while configuring the security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home.html").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    /**
     * Configures an in-memory user details service. This method creates a user with
     * the username "user" and password "password", and assigns the "USER" role to the
     * user. The password is encoded using a default password encoder for simplicity.
     *
     * @return the configured UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}

