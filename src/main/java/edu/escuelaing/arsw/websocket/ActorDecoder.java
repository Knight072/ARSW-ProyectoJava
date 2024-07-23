package edu.escuelaing.arsw.websocket;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * The ActorDecoder class implements the Jakarta WebSocket Decoder.Text interface to decode JSON messages
 * into {@link JsonNode} objects. It uses Jackson's ObjectMapper for JSON processing.
 */
public class ActorDecoder implements Decoder.Text<JsonNode> {
    /** The ObjectMapper instance used for JSON processing. */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Decodes a JSON string into a JsonNode object.
     *
     * @param s The JSON string to decode.
     * @return The decoded JsonNode object.
     * @throws DecodeException If an error occurs during decoding.
     */
    @Override
    public JsonNode decode(String s) throws DecodeException {
        try {
            return objectMapper.readTree(s);
        } catch (IOException e) {
            throw new DecodeException(s, "Error decoding message", e);
        }
    }

    /**
     * Checks if the given string can be decoded into a JsonNode.
     *
     * @param s The string to check.
     * @return true if the string can be decoded; false otherwise.
     */
    @Override
    public boolean willDecode(String s) {
        try {
            objectMapper.readTree(s);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Initializes the decoder with the given EndpointConfig.
     * This method can be used to perform any necessary initialization.
     *
     * @param config The EndpointConfig to initialize with.
     */
    @Override
    public void init(EndpointConfig config) {
        // Initialization code if needed
    }

    /**
     * Cleans up any resources used by the decoder.
     * This method can be used to perform any necessary cleanup.
     */
    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}



