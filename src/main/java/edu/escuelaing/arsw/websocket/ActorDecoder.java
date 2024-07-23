package edu.escuelaing.arsw.websocket;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class ActorDecoder implements Decoder.Text<JsonNode> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JsonNode decode(String s) throws DecodeException {
        try {
            return objectMapper.readTree(s);
        } catch (IOException e) {
            throw new DecodeException(s, "Error decoding message", e);
        }
    }

    @Override
    public boolean willDecode(String s) {
        try {
            objectMapper.readTree(s);
            return true;
        } catch (IOException e) {
            return false;
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


