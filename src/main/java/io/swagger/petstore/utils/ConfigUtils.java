package io.swagger.petstore.utils;

import java.util.Properties;

public class ConfigUtils {
    private Properties properties;
    private static ConfigUtils instance;
    private final String filePath;

    private ConfigUtils() {
        String env = System.getProperty("env", "TESTING");
        filePath = switch (env) {
            case "TESTING" -> "src/main/resources/properties/testing.properties";
            default -> throw new RuntimeException("Environment isn't supported");
        };
        properties = PropertiesUtils.loadProperties(filePath);
    }

    public static synchronized ConfigUtils getInstance() {
        if (instance == null) {
            instance = new ConfigUtils();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "");
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        PropertiesUtils.updateProperty(filePath, key, value);
    }
}