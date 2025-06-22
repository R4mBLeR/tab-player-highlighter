package net.r4mble;

import net.fabricmc.api.ClientModInitializer;
import net.r4mble.event.PlayerIvents;

import java.util.Map;

public class TabPlayerHighlighterClient implements ClientModInitializer {
    public static Map<String, String> players_prefixes;
    public static ModConfig CONFIG;

    @Override
    public void onInitializeClient() {
        CONFIG = ModConfig.load();
        PlayerIvents.registerEvents();
    }
}