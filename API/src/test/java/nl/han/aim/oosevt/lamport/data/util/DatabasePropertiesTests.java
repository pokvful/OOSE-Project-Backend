package nl.han.aim.oosevt.lamport.data.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabasePropertiesTests {
    @Test
    void databasePropertiesInits() {
        DatabaseProperties.init();

        Assertions.assertEquals(DatabaseProperties.connectionString(), "connectionString");
    }
}
