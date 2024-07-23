package edu.escuelaing.arsw.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebSecurityConfig.class)
public class WebSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPublicEndpoints() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void testProtectedEndpointUnauthenticated() throws Exception {
        mockMvc.perform(get("/some-protected-endpoint"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testProtectedEndpointAuthenticated() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection());
    }
}
