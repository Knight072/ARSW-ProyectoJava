package edu.escuelaing.arsw.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.DecodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActorDecoderTest {

    private ActorDecoder decoder;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        decoder = new ActorDecoder();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testDecodeValidJson() throws DecodeException, JsonProcessingException {
        String jsonString = "{\"id\":\"1\",\"positionX\":5,\"positionY\":10}";
        JsonNode expectedNode = objectMapper.readTree(jsonString);

        JsonNode decodedNode = decoder.decode(jsonString);

        assertNotNull(decodedNode, "El nodo decodificado no debe ser null");
        assertEquals(expectedNode, decodedNode, "El nodo decodificado debe coincidir con el nodo esperado");
    }

    @Test
    public void testDecodeInvalidJson() {
        String invalidJsonString = "{\"id\":\"1\",\"positionX\":5,\"positionY\":}";

        DecodeException thrownException = assertThrows(DecodeException.class, () -> {
            decoder.decode(invalidJsonString);
        });

        assertEquals("Error decoding message", thrownException.getMessage(), "El mensaje de excepción debe ser 'Error decoding message'");
    }

    @Test
    public void testWillDecodeValidJson() {
        String validJsonString = "{\"id\":\"1\",\"positionX\":5,\"positionY\":10}";

        assertTrue(decoder.willDecode(validJsonString), "Debería poder decodificar una cadena JSON válida");
    }

    @Test
    public void testWillDecodeInvalidJson() {
        String invalidJsonString = "{\"id\":\"1\",\"positionX\":5,\"positionY\":}";

        assertFalse(decoder.willDecode(invalidJsonString), "No debería poder decodificar una cadena JSON inválida");
    }
}

