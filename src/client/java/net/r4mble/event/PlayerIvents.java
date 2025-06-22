package net.r4mble.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.network.ServerInfo;
import net.r4mble.TabPlayerHighlighterClient;
import net.r4mble.util.TabPlayerHighlighterAPI;


@Environment(EnvType.CLIENT)
public class PlayerIvents {

    public static void registerEvents() {
        onPlayerJoinedServer();
    }

    private static void onPlayerJoinedServer() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            client.execute(() -> {
                ServerInfo serverInfo = client.getCurrentServerEntry();
                if (serverInfo != null) {
                    TabPlayerHighlighterClient.players_prefixes = TabPlayerHighlighterAPI.getPlayersWithRoles();
                }
            });
        });
    }
}
