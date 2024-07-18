package edu.escuelaing.arsw;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ActorEncoder implements Encoder.Text<Object> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String encode(Object object) throws EncodeException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(EndpointConfig config) {
        // Inicialización si es necesaria
    }

    @Override
    public void destroy() {
        // Limpieza si es necesaria
    }
}

