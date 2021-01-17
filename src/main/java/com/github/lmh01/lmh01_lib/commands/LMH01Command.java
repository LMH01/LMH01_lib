package com.github.lmh01.lmh01_lib.commands;

import com.github.lmh01.lmh01_lib.helpers.ChatHelper;
import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.util.References;
import com.github.lmh01.lmh01_lib.util.SubModManager;
import com.github.lmh01.lmh01_lib.util.UpdateCheckerManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class LMH01Command {

    //private static String[] string = {"1", "2"}; Activate this String when you use second help page.
    private static final String[] ALLOWED_PAGES = {"1"}; //Delete this line when using second help page.
    /*SuggestionProvider for /lmh01 help [page]*/
    private static final SuggestionProvider<CommandSource> SUGGEST_PAGE = (source, builder) -> ISuggestionProvider.suggest(ALLOWED_PAGES, builder);

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("lmh01")
                .then(Commands.literal("help").executes(source -> castHelpSubCommand(1))
                        .then(Commands.argument("page", IntegerArgumentType.integer(1,1)).suggests(SUGGEST_PAGE).executes(source -> {
                            int page = IntegerArgumentType.getInteger(source, "page");
                            DebugHelper.sendDebugInformation("Page: " + page, 4);
                            return castHelpSubCommand(page);
                        })
        )).then(Commands.literal("config").executes(source -> castConfigSubCommand(source.getSource()))).then(Commands.literal("version").executes(source -> castVersionSubCommand(source.getSource())))
          .then(Commands.literal("links").executes(source -> castLinksSubCommand()))
          .then(Commands.literal("mods").executes(source -> castModsSubCommand())));
    }
    private static int castConfigSubCommand(CommandSource source){
        source.sendFeedback(new TranslationTextComponent("commands.lmh01.config.config_not_available").mergeStyle(TextFormatting.RED), true);
        return 1;
    }
    private static int castVersionSubCommand(CommandSource source){
        UpdateCheckerManager.printLMH01LibVersionToChat(source);
        return 1;
    }
    private static int castLinksSubCommand(){
        ChatHelper.sendTranslatedChatMessage("commands.lmh01.links.firstLine", TextFormatting.DARK_GREEN);
        ChatHelper.sendTranslatedChatMessage("commands.lmh01.links.lmh01_lib_on_curseforge", TextFormatting.GOLD, References.MOD_WEBSITE_URL, "commands.lmh01.links.lmh01_lib_on_curseforge.tooltip");
        ChatHelper.sendTranslatedChatMessage("commands.lmh01.links.lmh01_lib_on_github", TextFormatting.GOLD, References.MOD_GITHUB_URL, "commands.lmh01.links.lmh01_lib_on_github.tooltip");
        ChatHelper.sendTranslatedChatMessage("commands.lmh01.links.lmh01_mods_on_twitter", TextFormatting.GOLD, References.LMH01_MODS_ON_TWITTER, "commands.lmh01.links.lmh01_mods_on_twitter.tooltip");
        return 1;
    }
    private static int castModsSubCommand(){
        if(SubModManager.getModCount()==0 && SubModManager.getModAddonCount()==0){
            ChatHelper.sendTranslatedChatMessage("commands.lmh01.mods.firstLine_without_mods", TextFormatting.DARK_GREEN);
        }else{
            ChatHelper.sendTranslatedChatMessage("commands.lmh01.mods.firstLine_with_mods", TextFormatting.DARK_GREEN);
            SubModManager.printSummary(true, false,true);
            ChatHelper.sendTranslatedChatMessage("text.lmh01.click_version_hint", TextFormatting.GRAY);
        }
        return 1;
    }
    private static int castHelpSubCommand(int page){
        if (page == 1) {
            /*6 commands per help site*/
            ChatHelper.sendTranslatedChatMessage("commands.lmh01.help.help_page_1", TextFormatting.AQUA);
            ChatHelper.sendTranslatedChatMessage("commands.lmh01.help.help", TextFormatting.DARK_GREEN);
            ChatHelper.sendTranslatedChatMessage("commands.lmh01.help.config", TextFormatting.DARK_GREEN);
            ChatHelper.sendTranslatedChatMessage("commands.lmh01.help.version", TextFormatting.DARK_GREEN);
            ChatHelper.sendTranslatedChatMessage("commands.lmh01.help.links", TextFormatting.DARK_GREEN);
            ChatHelper.sendTranslatedChatMessage("commands.lmh01.help.mods", TextFormatting.DARK_GREEN);
        }else if (page == 2) {
            /*This can be reactivated once i need a second page*/
            //ChatHelper.sendTranslatedChatMessage("commands.lmh01.help.help_page_2", TextFormatting.AQUA);
        }
        return 1;
    }
}
