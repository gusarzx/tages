package org.utils;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigReader {

    private static Map<String, Object> config;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            config = mapper.readValue(new File("src/main/resources/config.json"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить файл конфигурации: " + e.getMessage());
        }
    }

    public static String getBaseUrl() {
        return (String) config.get("baseUrl");
    }

    public static String getWebDriverPath() {
        Map<String, String> webdriver = (Map<String, String>) config.get("webdriver");
        return webdriver.get("path");
    }

    public static String getBrowser() {
        Map<String, String> webdriver = (Map<String, String>) config.get("webdriver");
        return webdriver.get("browser");
    }

    public static int getImplicitWait() {
        Map<String, Integer> timeouts = (Map<String, Integer>) config.get("timeouts");
        return timeouts.get("implicitWait");
    }

    public static int getExplicitWait() {
        Map<String, Integer> timeouts = (Map<String, Integer>) config.get("timeouts");
        return timeouts.get("explicitWait");
    }

    public static boolean isGridEnabled() {
        Map<String, Object> grid = (Map<String, Object>) config.get("grid");
        return (boolean) grid.get("turnOn");
    }

    public static String getGridUrl() {
        Map<String, Object> grid = (Map<String, Object>) config.get("grid");
        return (String) grid.get("url");
    }

    public static String getBrowserVersion() {
        Map<String, Object> grid = (Map<String, Object>) config.get("grid");
        return (String) grid.get("browserVersion");
    }
}
