package nl.han.aim.oosevt.lamport.shared;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashProviderTests {
    private final HashProvider hashProvider = new HashProviderImpl();

    @Test
    void hashReturnsValidHash() {
        final String result = hashProvider.hash("Test");

        Assertions.assertNotNull(result);
    }

    @Test
    void matchReturnsTrueForValidMatch() {
        final String hash = hashProvider.hash("Test");

        Assertions.assertTrue(hashProvider.matches("Test", hash));
    }

    @Test
    void matchReturnsFalseForInvalidMatch() {
        final String hash = hashProvider.hash("Test");

        Assertions.assertFalse(hashProvider.matches("Test1", hash));
    }
}
