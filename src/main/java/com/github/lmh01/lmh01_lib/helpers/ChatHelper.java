package com.github.lmh01.lmh01_lib.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

public class ChatHelper {
    //TODO Find ways to clean up the class a little bit (integrate functions, maybe delete some to specific functions, rename functions)
    /**
     * Sends a chat message to the current player. Supports TextFormatting.
     * @param message The message to send.
     */
    public static void sendChatMessage(String message){
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), Minecraft.getInstance().player.getUniqueID());
        }else{
            ErrorHelper.addError("Can't send chat message: player is null - nullPointerException");
        }
    }

    /**
     * Sends a translated chat message to the current player.
     * @param translationKey The translation key for the message.
     * @param textFormatting The text formatting that should be used.
     */
    public static void sendTranslatedChatMessage(String translationKey, TextFormatting textFormatting){
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendMessage(new TranslationTextComponent(translationKey).mergeStyle(textFormatting), Minecraft.getInstance().player.getUniqueID());
        }else{
            ErrorHelper.addError("Can't send chat message: player is null - nullPointerException");
        }
    }

    /**
     * Sends a translated chat message with a clickable link into the chat.
     * @param translationKey The translation key for the message.
     * @param textFormatting The text formatting that should be used.
     * @param link The link.
     */
    public static void sendTranslatedClickableLink(String translationKey, TextFormatting textFormatting, String link){
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendMessage(new TranslationTextComponent(translationKey).mergeStyle(textFormatting).modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,link))), Minecraft.getInstance().player.getUniqueID());
        }else{
            ErrorHelper.addError("Can't send chat message: player is null - nullPointerException");
        }
    }
    /**
     * Sends a translated chat message with a clickable link into the chat.
     * @param translationKey The translation key for the message.
     * @param textFormatting The text formatting that should be used.
     * @param link The link.
     * @param toolTipTranslationKey The tooltip translation key.
     */
    public static void sendTranslatedClickableLinkWithTranslatedToolTip(String translationKey, TextFormatting textFormatting, String link, String toolTipTranslationKey){
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendMessage(new TranslationTextComponent(translationKey).mergeStyle(textFormatting).modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,link))).modifyStyle(style -> style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent(toolTipTranslationKey)))), Minecraft.getInstance().player.getUniqueID());
        }else{
            ErrorHelper.addError("Can't send chat message: player is null - nullPointerException");
        }
    }

    /**
     * Sends a chat message with a clickable link into the chat.
     * @param message The message.
     * @param link The link.
     */
    public static void sendClickableLink(String message, String link){
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendMessage(new StringTextComponent(message).modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,link))), Minecraft.getInstance().player.getUniqueID());
        }else{
            ErrorHelper.addError("Can't send chat message: player is null - nullPointerException");
        }
    }

    /**
     * Sends a message with clickable link in to the chat. The link has a tooltip.
     * @param message The message to sent.
     * @param link The link-
     * @param toolTipTranslationKey The translation key for the tooltip.
     */
    public static void sendClickableLinkWithTranslatedToolTip(String message, String link, String toolTipTranslationKey){
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendMessage(new StringTextComponent(message).modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,link))).modifyStyle(style -> style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent(toolTipTranslationKey)))), Minecraft.getInstance().player.getUniqueID());
        }else{
            ErrorHelper.addError("Can't send chat message: player is null - nullPointerException");
        }
    }

    /**
     * Sends a message to the command sender. Supports TextFormatting.
     * @param message The message.
     * @param source The source.
     */
    public static void sendCommandFeedback(String message, CommandSource source){
        source.sendFeedback(new StringTextComponent(message), true);
    }

    /**
     * Sends a message to the command sender that can be clicked to open a link. Supports TextFormatting.
     * @param message The message.
     * @param source The source.
     * @param link The link.
     */
    public static void sendCommandFeedbackWithClickableLink(String message, CommandSource source, String link){
        source.sendFeedback(new StringTextComponent(message).modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link))), true);
    }

    /**
     * Sends a translated text to the command sender.
     * @param translationKey The translation key for the message.
     * @param textFormatting The text formatting that should be used.
     * @param source The source.
     */
    public static void sendTranslatedCommandFeedback(String translationKey, TextFormatting textFormatting, CommandSource source){
        source.sendFeedback(new TranslationTextComponent(translationKey).mergeStyle(textFormatting), true);
    }
}
