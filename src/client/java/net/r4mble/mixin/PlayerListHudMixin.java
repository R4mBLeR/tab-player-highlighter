package net.r4mble.mixin;

import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import net.r4mble.TabPlayerHighlighterClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerListHud.class)
public abstract class PlayerListHudMixin {

    @Inject(method = "getPlayerName", at = @At("HEAD"), cancellable = true)
    private void onGetPlayerName(PlayerListEntry entry, CallbackInfoReturnable<Text> cir) {
        Text displayName = entry.getDisplayName() != null
                ? entry.getDisplayName()
                : Text.literal(entry.getProfile().getName());

        if (TabPlayerHighlighterClient.players_prefixes == null) {
            return;
        }
        String uuid = entry.getProfile().getId().toString();

        String prefix = TabPlayerHighlighterClient.players_prefixes.get(uuid);
        if (prefix != null && !prefix.isEmpty()) {
            Text modifiedName = Text.empty()
                    .append(Text.literal(prefix))
                    .append(" ")
                    .append(displayName);

            cir.setReturnValue(modifiedName);
        }
    }

    /*@Inject(method = "collectPlayerEntries", at = @At("RETURN"), cancellable = true)
    private void sortPlayerEntries(CallbackInfoReturnable<List<PlayerListEntry>> cir) {
        List<PlayerListEntry> originalList = cir.getReturnValue();
        if (!ModConfig.HANDLER.instance().priorityMode) {
            cir.setReturnValue(originalList);
        } else {
            HashMap<String, PlayerListEntry> tabList = new HashMap<>();

            for (PlayerListEntry entry : originalList) {
                tabList.put(entry.getProfile().getId().toString(), entry);
            }

            List<PlayerListEntry> modifiedList = new ArrayList<>();
            List<PlayerListEntry> priorityList = new ArrayList<>();

            for (Map.Entry<String, String> entry : TabPlayerHighlighterClient.players_prefixes.entrySet()) {
                if (tabList.containsKey(entry.getKey())) {
                    priorityList.add(tabList.get(entry.getKey()));
                }
            }
            for (PlayerListEntry entry : originalList) {
                if (!TabPlayerHighlighterClient.players_prefixes.containsKey(entry.getProfile().getId().toString())) {
                    modifiedList.add(entry);
                }
            }
            priorityList.addAll(modifiedList);
            cir.setReturnValue(priorityList);
        }
    }*/
}
