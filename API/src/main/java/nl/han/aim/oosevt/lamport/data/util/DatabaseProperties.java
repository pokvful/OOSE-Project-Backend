package nl.han.aim.oosevt.lamport.data.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {
    private static final Logger LOGGER = Logger.getLogger(DatabaseProperties.class.getName());
    private static String connectionString;

    public static void init() {
        final Properties properties = new Properties();
        try {
            properties.load(DatabaseProperties.class.getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));
            connectionString = properties.getProperty("connectionString");
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Cant access property file database.properties", e);
        }
    }

    public static String connectionString() {
        return connectionString;
    }
}
