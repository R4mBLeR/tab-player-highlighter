package net.r4mble;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;

public class ModConfig {
    public boolean onlineMod = true;
    public String API_URL = "http://localhost:3000/get_roles";
    public HashMap<String, String> playersPrefixes = new HashMap<>();

    public static ModConfig load() {
        File configFile = new File("./config/tab_player_highlighter.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            if (!configFile.exists()) {
                ModConfig defaultConfig = new ModConfig();
                String json = gson.toJson(defaultConfig);
                Files.createDirectories(configFile.getParentFile().toPath());
                Files.write(configFile.toPath(), json.getBytes());
                return defaultConfig;
            } else {
                return gson.fromJson(new FileReader(configFile), ModConfig.class);
            }
        } catch (IOException e) {
            return new ModConfig(); // Возвращаем дефолтные значения
        }
    }
}