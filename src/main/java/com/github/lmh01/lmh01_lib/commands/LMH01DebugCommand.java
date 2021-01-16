package com.github.lmh01.lmh01_lib.commands;

import com.github.lmh01.lmh01_lib.util.SubModManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class LMH01DebugCommand {

    //TODO Make this command only available in debug mode (Debug mode can be turned off and on in the config)
    private static final SuggestionProvider<CommandSource> SUGGEST_COLOR = (source, builder) -> ISuggestionProvider.suggest(TextFormatting.getValidValues(true, false).stream(), builder);

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("lmh01_debug").executes(source -> lmh01(source.getSource(), source.getSource().asPlayer()))
                .then(Commands.argument("target", EntityArgument.player()).executes(source -> lmh01(source.getSource(), EntityArgument.getPlayer(source, "target"))))
                .then(Commands.argument("color", StringArgumentType.string()).suggests(SUGGEST_COLOR).executes(source -> lmh01(source.getSource(), EntityArgument.getPlayer(source, "target"), StringArgumentType.getString(source, "color")))));
    }
    private static int lmh01(CommandSource source, PlayerEntity player){
        source.sendFeedback(new TranslationTextComponent("commands.lmh01_test", player.getDisplayName()), true);
        SubModManager.printSummary(true, true, false);
        SubModManager.printSummary(false, false, true);
        return 1;
    }
    private static int lmh01(CommandSource source, PlayerEntity player, String color){
        source.sendFeedback(new TranslationTextComponent("commands.lmh01_test.color", TextFormatting.getValueByName(color).toString(), player.getDisplayName()), true);
        return 1;
    }
}
