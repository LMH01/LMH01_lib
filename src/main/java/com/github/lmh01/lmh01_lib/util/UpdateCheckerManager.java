package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.*;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class UpdateCheckerManager {
    public static ArrayList<String> updateAvailable = new ArrayList<>();
    public static ArrayList<String> newestVersion = new ArrayList<>();
    public static final CountDownLatch waitForUpdatesFinished = new CountDownLatch(SubModManager.getModCount()+1);
    public static boolean newLMH01_libVersionAvailable = false;
    public static String newestLMH01_libVersion = "";
    public static int numberOfAvailableUpdates = 0;

    /*
    * The UpdateCheckerManager organizes all update check requests from the different lmh01 mods.
    *
    * When a new version is found the updateCheckerManager will tell the user and give him a download link
    *
    * New and Current Versions are stored here in Strings and will be compared when checking if an update is available.
    * */


    /**
     * Checks all registered lmh01 mods for updates. Register a new sub-mod fia {@link SubModManager#registerSubMod(String, String, String, String, String)}
     */
    public static void checkForUpdates(){
        checkLMH01_libForUpdates();
        int modNumber = 1;
        for (int i = 0; i < SubModManager.getModCount()*4; i= i+4){
            DebugHelper.sendDebugInformation("Checking for updates with parameters: updateURL: " + SubModManager.registeredMods.get(i+3) +
                    " modid: " + SubModManager.registeredMods.get(i) + " currentVersion: " + SubModManager.registeredMods.get(i+2), 4, 0);
            UpdateCheckerHelper.checkForUpdates(SubModManager.registeredMods.get(i+3), SubModManager.registeredMods.get(i), SubModManager.registeredMods.get(i+2));
            modNumber++;

        }
    }
    /*Shows Loading Summary when all update checkers are done*/
    public static Runnable runnableWaitForUpdatesFinishedAndSendLoadingSummary = () -> {
        try {
            waitForUpdatesFinished.await();
            LoadingSummary.showLoadingSummary();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    /**
     * Checks LMH01_lib for updates
     */
    private static void checkLMH01_libForUpdates(){
        new Thread("LMH01_lib update checker"){
            public void run(){
                String newestVersion = WebHelper.getContentFromURL(References.UPDATE_URL);
                newestLMH01_libVersion = newestVersion;
                DebugHelper.sendDebugInformation("newestLMH01_libVersion: " + newestLMH01_libVersion,5);
                if(!newestVersion.equals(References.VERSION)){
                    newLMH01_libVersionAvailable = true;
                }
                waitForUpdatesFinished.countDown();
            }
        }.start();
    }

    /**
     * This function will print into the chat what updates are available
     */
    public static void printChatNotification(boolean calledFromEvents){
        if(SubModManager.getModCount()!=0 && UpdateCheckerManager.numberOfAvailableUpdates!=0 ||newLMH01_libVersionAvailable){
            if(UpdateCheckerManager.numberOfAvailableUpdates==1){
                ChatHelper.sendTranslatedChatMessage("text.lmh01.update_available", TextFormatting.GOLD);
            }else{
                ChatHelper.sendTranslatedChatMessage("text.lmh01.updates_available", TextFormatting.GOLD);
            }
            if(newLMH01_libVersionAvailable){
                ChatHelper.sendClickableLinkWithTranslatedToolTip(TextFormatting.GOLD + "LMH01_lib (" + References.VERSION + "): " + TextFormatting.DARK_AQUA + UpdateCheckerManager.newestLMH01_libVersion, References.DOWNLOAD_URL, "tooltip.click_to_open_lmh01_lib_download_page");
            }
            SubModManager.printSummary(true, true, false);
            ChatHelper.sendTranslatedChatMessage("text.lmh01.click_version_hint", TextFormatting.GRAY);
        }else{
            if(!calledFromEvents){
                ChatHelper.sendTranslatedChatMessage("text.lmh01.no_updates_available", TextFormatting.GREEN);
            }
        }
    }
    public static void printLMH01LibVersionToChat(CommandSource source){
        DebugHelper.sendDebugInformation("newestLMH01_libVersion: " + newestLMH01_libVersion,5);
        DebugHelper.sendDebugInformation("currentLMH01_libVersion: " + References.VERSION,5);
        if(References.VERSION.equals(newestLMH01_libVersion)){
            ChatHelper.sendCommandFeedback(TextFormatting.GOLD + "Current LMH01_lib version: " + TextFormatting.DARK_GREEN + References.VERSION, source);
        }else{
            Minecraft.getInstance().player.sendMessage(new StringTextComponent(TextFormatting.GOLD + "Current LMH01_lib version: " + TextFormatting.YELLOW + References.VERSION + TextFormatting.GOLD + "\nThis version is " + TextFormatting.RED + "Outdated!" + TextFormatting.GOLD + " Click this message to open the download page.").modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, References.DOWNLOAD_URL))).modifyStyle(style -> style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("tooltip.click_to_open_lmh01_lib_download_page")))), Minecraft.getInstance().player.getUniqueID());
        }
    }

}
