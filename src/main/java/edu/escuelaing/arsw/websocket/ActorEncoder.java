package edu.escuelaing.arsw.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The ActorEncoder class implements the Jakarta WebSocket Encoder.Text interface to encode objects
 * into JSON strings. It uses Jackson's ObjectMapper for JSON processing.
 */
public class ActorEncoder implements Encoder.Text<Object> {
    /** The ObjectMapper instance used for JSON processing. */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Encodes an object into a JSON string.
     *
     * @param object The object to encode.
     * @return The encoded JSON string.
     * @throws EncodeException If an error occurs during encoding.
     */
    @Override
    public String encode(Object object) throws EncodeException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new EncodeException(object, "Error encoding object to JSON", e);
        }
    }

    /**
     * Initializes the encoder with the given EndpointConfig.
     * This method can be used to perform any necessary initialization.
     *
     * @param config The EndpointConfig to initialize with.
     */
    @Override
    public void init(EndpointConfig config) {
        // Initialization code if needed
    }

    /**
     * Cleans up any resources used by the encoder.
     * This method can be used to perform any necessary cleanup.
     */
    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}


