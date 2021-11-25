package nl.han.aim.oosevt.lamport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;

public class ObjectAssertions {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        Assertions.assertEquals(toJson(expected), toJson(actual));
    }
}
