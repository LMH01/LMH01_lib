package com.github.lmh01.lmh01_lib.commands;

import com.github.lmh01.lmh01_lib.helpers.ChatHelper;
import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.util.LoadingSummary;
import com.github.lmh01.lmh01_lib.util.SubModManager;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.impl.DifficultyCommand;
import net.minecraft.command.impl.FillCommand;
import net.minecraft.command.impl.GameModeCommand;
import net.minecraft.command.impl.GameRuleCommand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class LMH01Command {
    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("lmh01").executes(source -> {
            return lmh01(source.getSource(), source.getSource().asPlayer(), "");
        /*First Command*/
        }).then(Commands.literal("command").executes(source -> {
            return lmh01(source.getSource(), source.getSource().asPlayer(), "loadingsummary");
            })./*First Sub-Command*/
                then(Commands.literal("test").executes(source -> {
            return lmh01(source.getSource(), source.getSource().asPlayer(), "test");
            })./*Second Sub-Command*/
                then(Commands.literal("testStage").executes(source -> {
                    return lmh01(source.getSource(), source.getSource().asPlayer(), "secondtest");
            })
        /*Second Command*/
        ))).then(Commands.literal("updates").executes(source -> {
            return lmh01(source.getSource(), source.getSource().asPlayer(), "updates");
        })));
    }

    /**
     * This function is called from {@link LMH01Command#register(CommandDispatcher)} to execute all different sub-commands.
     * @param source CommandSource
     * @param player PlayerEntity
     * @param usage String that tells what command part to execute
     * @return Returns 1
     */
    private static int lmh01(CommandSource source, PlayerEntity player, String usage){
        //TODO implement all sub-commands from the 1.8.9 lmh01_lib, then delete the placeholders
        DebugHelper.sendDebugInformation("Casting Command lmh01: " + usage, 2, 0);
        switch (usage) {
            case "":
                source.sendFeedback(new TranslationTextComponent("commands.lmh01", player.getDisplayName()), true);
                break;
            case "loadingsummary":
                source.sendFeedback(new TranslationTextComponent("commands.lmh01.loadingsummary", player.getDisplayName()), true);
                LoadingSummary.showLoadingSummary();
                break;
            case "test":
                source.sendFeedback(new TranslationTextComponent("commands.lmh01.test", player.getDisplayName()), true);
                break;
            case "test2":
                source.sendFeedback(new TranslationTextComponent("commands.lmh01.test2", player.getDisplayName()), true);
                break;
            case "secondtest":

                break;
            case "updates":
                SubModManager.printChatNotification();
                break;
        }
        return 1;
    }
}
