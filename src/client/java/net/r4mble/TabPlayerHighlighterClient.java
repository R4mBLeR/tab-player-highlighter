package net.r4mble;

import net.fabricmc.api.ClientModInitializer;
import net.r4mble.event.PlayerIvents;

import java.util.Map;

public class TabPlayerHighlighterClient implements ClientModInitializer {
    public static Map<String, String> players_prefixes;

    @Override
    public void onInitializeClient() {
        ModConfig.HANDLER.load();
        PlayerIvents.registerEvents();
    }
}