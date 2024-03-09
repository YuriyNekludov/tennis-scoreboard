package edu.tennis_scoreboard.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertyUtil {

    private final Properties SETTINGS = new Properties();

    public Properties getSettings() {
        return SETTINGS;
    }

    void loadProperties() {
        try (InputStream inputStream = PropertyUtil.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            SETTINGS.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
