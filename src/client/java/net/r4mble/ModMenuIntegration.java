package net.r4mble;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Tab Player Highlighter"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("text.option.group_name"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.translatable("text.option.priority_mode"))
                                .description(OptionDescription.of(Text.translatable("text.option.priority_mode_description")))
                                .binding(true, () -> ModConfig.HANDLER.instance().priorityMode, newVal -> ModConfig.HANDLER.instance().priorityMode = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())
                .save(() -> ModConfig.HANDLER.save())
                .build()
                .generateScreen(parentScreen);
    }
}