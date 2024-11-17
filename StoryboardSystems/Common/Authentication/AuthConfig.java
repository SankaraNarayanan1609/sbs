package com.Vcidex.StoryboardSystems.Common.Authentication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthConfig {
    private static final String CONFIG_PATH = System.getProperty("configFilePath", "src/main/resources/config.json");
    private static final JSONObject config;

    static {
        try {
            String jsonText = Files.readString(Paths.get(CONFIG_PATH));
            config = new JSONObject(jsonText);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file: " + CONFIG_PATH, e);
        }
    }

    // Method to get the application URL for the given environment
    public static String getAppUrl(String environment) {
        JSONObject envConfig = config.optJSONObject(environment);
        if (envConfig == null) {
            throw new RuntimeException("Environment not found: " + environment);
        }
        String appUrl = envConfig.optString("appUrl", "");
        if (appUrl.isEmpty()) {
            throw new RuntimeException("App URL not found for environment: " + environment);
        }
        return appUrl;
    }

    // Method to get user data (companyCode, userName, userId, password) from config based on environment and user index
    public static String getUserData(String environment, int userIndex, String dataType) {
        JSONObject envConfig = config.optJSONObject(environment);
        if (envConfig == null) {
            throw new RuntimeException("Environment not found: " + environment);
        }

        JSONArray users = envConfig.optJSONArray("users");
        if (users == null || users.length() <= userIndex) {
            throw new RuntimeException("User not found at index: " + userIndex);
        }

        JSONObject user = users.getJSONObject(userIndex);
        String data = user.optString(dataType, "");
        if (data.isEmpty()) {
            throw new RuntimeException(dataType + " not found for user index: " + userIndex);
        }

        return data;
    }
}