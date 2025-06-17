package net.r4mble.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.network.ServerInfo;
import net.r4mble.TabPlayerHighlighter;
import net.r4mble.util.TabPlayerHighlighterAPI;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class PlayerIvents {

    public static void registerEvents() {
        onPlayerJoinedServer();
    }

    private static void onPlayerJoinedServer() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            client.execute(() -> {
                ServerInfo serverInfo = client.getCurrentServerEntry();
                Map<String, String[]> players;
                if (serverInfo != null) {
                    TabPlayerHighlighter.LOGGER.info(String.valueOf(serverInfo.players.online()));
                    players = TabPlayerHighlighterAPI.getPlayersWithRoles();
                } else {
                    players = TabPlayerHighlighterAPI.getPlayersWithRoles();
                }
                if (players != null) {
                    TabPlayerHighlighter.LOGGER.info(players.toString());
                }
            });
        });
    }

}
