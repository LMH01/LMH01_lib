package com.github.lmh01.lmh01_lib.commands;

import com.github.lmh01.lmh01_lib.LMH01_lib;
import com.github.lmh01.lmh01_lib.helpers.ChatHelper;
import com.github.lmh01.lmh01_lib.helpers.CommandHelper;
import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.util.ChildModManager;
import com.github.lmh01.lmh01_lib.util.References;
import com.github.lmh01.lmh01_lib.util.UpdateCheckerManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.text.TextFormatting;

public class LMH01Command {

    //private static String[] string = {"1", "2"}; Activate this String when you use second help page.
    private static final String[] ALLOWED_PAGES = {"1"}; //Delete this line when using second help page.
    /*SuggestionProvider for /lmh01 help [page]*/
    private static final SuggestionProvider<CommandSource> SUGGEST_PAGE = (source, builder) -> ISuggestionProvider.suggest(ALLOWED_PAGES, builder);

    //TODO Write sub command with which all warnings and errors can be viewed.

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("lmh01")
                .then(Commands.literal("help").executes(source -> castHelpSubCommand(1, source.getSource()))
                        .then(Commands.argument("page", IntegerArgumentType.integer(1,1)).suggests(SUGGEST_PAGE).executes(source -> {
                            int page = IntegerArgumentType.getInteger(source, "page");
                            DebugHelper.sendDebugInformation("Page: " + page, 4);
                            return castHelpSubCommand(page,  source.getSource());
                        })
        )).then(Commands.literal("config").executes(source -> castConfigSubCommand(source.getSource()))).then(Commands.literal("version").executes(source -> castVersionSubCommand(source.getSource())))
          .then(Commands.literal("links").executes(source -> castLinksSubCommand(source.getSource())))
          .then(Commands.literal("mods").executes(source -> castModsSubCommand(source.getSource()))));
    }
    private static int castConfigSubCommand(CommandSource source){
        CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.config.config_not_available", TextFormatting.RED, source, true);
        return 1;
    }
    private static int castVersionSubCommand(CommandSource source){
        UpdateCheckerManager.printLMH01LibVersionToChat(source);
        return 1;
    }
    private static int castLinksSubCommand(CommandSource source){
        DebugHelper.sendDebugInformation("isRunningOnClientSide: " + LMH01_lib.isRunningOnClientSide(), 5);
        /* TODO This is a temporary solution as i can't get this to work:
         *  public static int castLinksSubCommand(CommandSource cource, ICommandSender iCommandSender){
         *      if(iCommandSource instanceof PlayerEntity){Execution of command in chat}else{execution of command in console}
         *  }
         *  I just don't know where i am supposed to get the ICommandSource from
         *  Because this does not work the player will get the same message as the console when playing on a server.
         */
        if(LMH01_lib.isRunningOnClientSide()){
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.links.firstLine", TextFormatting.DARK_GREEN, source, true);
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.links.lmh01_lib_on_curseforge", TextFormatting.GOLD, source,true, References.MOD_WEBSITE_URL, "commands.lmh01_lib.links.lmh01_lib_on_curseforge.tooltip");
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.links.lmh01_lib_on_github", TextFormatting.GOLD, source, true, References.MOD_GITHUB_URL, "commands.lmh01.lmh01_lib.lmh01_lib_on_github.tooltip");
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.links.lmh01_mods_on_twitter", TextFormatting.GOLD, source, true, References.LMH01_MODS_ON_TWITTER, "commands.lmh01_lib.links.lmh01_mods_on_twitter.tooltip");
        }else{
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.links.firstLine", source, true);
            CommandHelper.sendCommandFeedback("LMH01_lib on Curseforge: " + References.MOD_WEBSITE_URL, source, true);
            CommandHelper.sendCommandFeedback("LMH01_lib on Github: " + References.MOD_GITHUB_URL, source, true);
            CommandHelper.sendCommandFeedback("LMH01_lib on Twitter: " + References.LMH01_MODS_ON_TWITTER, source, true);
        }
        return 1;
    }
    private static int castModsSubCommand(CommandSource source){
        if(ChildModManager.getModCount()==0 && ChildModManager.getModAddonCount()==0){
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.mods.firstLine_without_mods", TextFormatting.DARK_GREEN, source, true);
        }else{
            if(LMH01_lib.isRunningOnClientSide()){
                ChatHelper.sendTranslatedChatMessage("commands.lmh01_lib.mods.firstLine_with_mods", TextFormatting.DARK_GREEN);
                ChildModManager.printSummary(true, false,true);
            }else{
                CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.mods.firstLine_with_mods", TextFormatting.DARK_GREEN, source, true);
                ChildModManager.printSummary(false, false,true);
            }
            CommandHelper.sendTranslatedCommandFeedback("text.lmh01_lib.click_version_hint", TextFormatting.GRAY, source, true);
        }
        return 1;
    }
    private static int castHelpSubCommand(int page, CommandSource source){
        if (page == 1) {
            /*6 commands per help site*/
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.help.help_page_1", TextFormatting.AQUA, source, true);
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.help.help", TextFormatting.DARK_GREEN, source, true);
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.help.config", TextFormatting.DARK_GREEN, source, true);
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.help.version", TextFormatting.DARK_GREEN, source, true);
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.help.links", TextFormatting.DARK_GREEN, source, true);
            CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.help.mods", TextFormatting.DARK_GREEN, source, true);
        }else if (page == 2) {
            /*This can be reactivated once i need a second page*/
            //CommandHelper.sendTranslatedCommandFeedback("commands.lmh01_lib.help.help_page_2", TextFormatting.AQUA, source, true);
        }
        return 1;
    }
}
