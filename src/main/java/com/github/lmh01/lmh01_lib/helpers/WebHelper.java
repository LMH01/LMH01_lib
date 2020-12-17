package com.github.lmh01.lmh01_lib.helpers;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
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
            DebugHelper.sendDebugInformation("Content from URL: " + string, 2, 0);
            return string;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Opens a new browserTab with given url
     * @param url the url
     */
    public static void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
