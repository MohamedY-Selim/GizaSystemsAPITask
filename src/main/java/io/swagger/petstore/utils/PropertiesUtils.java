package io.swagger.petstore.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (InputStream in =
                     Thread.currentThread().getContextClassLoader().getResourceAsStream(stripSrcMainResources(filePath))) {
            if (in != null) {
                properties.load(in);
                return properties;
            }
        } catch (IOException ignored) {}

        try (InputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file: " + filePath, e);
        }
        return properties;
    }

    public static void updateProperty(String filePath, String key, String value) {
        Properties properties = loadProperties(filePath);
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            properties.setProperty(key, value);
            properties.store(out, null);
        } catch (IOException e) {
            throw new RuntimeException("Error updating property '" + key + "' in file: " + filePath, e);
        }
    }

    private static String stripSrcMainResources(String path) {
        String prefix = "src/main/resources/";
        return path.startsWith(prefix) ? path.substring(prefix.length()) : path;
    }
}