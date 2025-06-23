package net.r4mble.mixin;

import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import net.r4mble.TabPlayerHighlighter;
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

}