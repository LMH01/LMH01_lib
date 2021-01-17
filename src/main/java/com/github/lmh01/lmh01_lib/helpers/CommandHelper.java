package com.github.lmh01.lmh01_lib.helpers;

import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

public class CommandHelper {

    /**
     * Sends a message to the command sender. Supports TextFormatting.
     * @param message The message.
     * @param source The source.
     * @param allowLogging True if logging of message is allowed.
     */
    public static void sendCommandFeedback(String message, CommandSource source, boolean allowLogging){
        source.sendFeedback(new StringTextComponent(message), allowLogging);
    }

    /**
     * Sends a message to the command sender that can be clicked to open a link. Supports TextFormatting. Hovering over the message reveals a translated tooltip.
     * @param message The message.
     * @param source The source.
     * @param allowLogging True if logging of message is allowed.
     * @param link The link.
     * @param toolTipTranslationKey The tooltip translation key.
     */
    public static void sendCommandFeedback(String message, CommandSource source, boolean allowLogging, String link, String toolTipTranslationKey){
        source.sendFeedback(new StringTextComponent(message).modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link))).modifyStyle(style -> style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent(toolTipTranslationKey)))), allowLogging);
    }

    /**
     * Sends a translated message to the command sender.
     * @param translationKey The translation key for the message.
     * @param source The source.
     * @param allowLogging True if logging of message is allowed.
     */
    public static void sendTranslatedCommandFeedback(String translationKey, CommandSource source, boolean allowLogging){
        source.sendFeedback(new TranslationTextComponent(translationKey), allowLogging);
    }

    /**
     * Sends a translated message to the command sender.
     * @param translationKey The translation key for the message.
     * @param textFormatting The text formatting that should be used.
     * @param source The source.
     * @param allowLogging True if logging of message is allowed.
     */
    public static void sendTranslatedCommandFeedback(String translationKey, TextFormatting textFormatting, CommandSource source, boolean allowLogging){
        source.sendFeedback(new TranslationTextComponent(translationKey).mergeStyle(textFormatting), allowLogging);
    }

    /**
     * Sends a message to the command sender that can be clicked to open a link. Hovering over the message reveals a translated tooltip.
     * @param translationKey The translation key for the message.
     * @param textFormatting  The text formatting that should be used.
     * @param source The source.
     * @param allowLogging True if logging of message is allowed.
     * @param link The link.
     * @param toolTipTranslationKey The tooltip translation key.
     */
    public static void sendTranslatedCommandFeedback(String translationKey, TextFormatting textFormatting, CommandSource source, boolean allowLogging, String link, String toolTipTranslationKey){
        source.sendFeedback(new TranslationTextComponent(translationKey).mergeStyle(textFormatting).modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link))).modifyStyle(style -> style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent(toolTipTranslationKey)))), allowLogging);
    }
}
