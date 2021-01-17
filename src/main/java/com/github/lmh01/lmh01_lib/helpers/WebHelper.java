package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.References;
import net.minecraft.util.text.TextFormatting;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class WebHelper {
    /**
     * This function will return a string from a specified webside. It is recommanded to call this function from
     * within a Thread to preserve system resources and to keep the code from getting frozen.
     * @param urlIn the URL to access
     * @return The content from the webpage as string
     */
    public static String getContentFromURL(String urlIn){
        try {
            java.net.URL url = new URL(urlIn);
            Scanner scanner = new Scanner(url.openStream());
            String string = scanner.nextLine();
            DebugHelper.sendDebugInformation("Content from URL: " + string, 4);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * Does not work as intended. Might be removed in a future build.
     * Use {@link ChatHelper#sendTranslatedChatMessage(String, TextFormatting, String)} or {@link ChatHelper#sendTranslatedChatMessage(String, TextFormatting, String, String)} to send a clickable message to the user that opens a website.
     * Opens a new browserTab with given url
     * @param url the url
     * @return returns true if the operation was successful
     */
    @Deprecated
    public static boolean openWebPage(String url) {
        boolean success;
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
            success = true;
        } catch (Exception e) {
            success = false;
            DebugHelper.sendException(e, References.MODID);
        }
        return success;
    }


}
