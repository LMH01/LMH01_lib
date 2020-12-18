package com.github.lmh01.lmh01_lib.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ChatHelper {
    /**
     * Sends a chat message to the current player. Supports TextFormatting.
     * @param message The message to send
     */
    public static void sendChatMessage(String message){
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), Minecraft.getInstance().player.getUniqueID());
        }else{
            ErrorHelper.addError("Can't send chat message: player is null - nullPointerException");
        }
    }

}
