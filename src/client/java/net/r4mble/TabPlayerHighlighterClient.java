package net.r4mble;

import net.fabricmc.api.ClientModInitializer;
import net.r4mble.event.PlayerIvents;

public class TabPlayerHighlighterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlayerIvents.registerEvents();
    }
}