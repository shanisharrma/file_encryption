package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnvConfig {
    public static Map<String, String> env;

    static {
        try {
            env = loadEnv(".env");
        } catch(IOException e) {
            throw new RuntimeException("Failed to load .env file.", e);
        }
    }

    private static Map<String, String> loadEnv(String filePath) throws IOException {
        Map <String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    map.put(parts[0].trim(), parts[1].trim());
                }

            }
        }
        return map;
    }

    public static String getEnv(String key) {
        return env.get(key);
    }
}
