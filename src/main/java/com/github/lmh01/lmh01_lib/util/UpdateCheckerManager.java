package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.*;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import java.util.ArrayList;

public class UpdateCheckerManager {
    private static final ArrayList<String> UPDATE_AVAILABLE = new ArrayList<>();
    private static final ArrayList<String> NEWEST_VERSION = new ArrayList<>();
    private static final ArrayList<String> REGISTERED_MODS = ChildModManager.getRegisteredModsArrayList();
    private static boolean newLMH01_libVersionAvailable = false;
    private static String newestLMH01_libVersion = "";
    private static int numberOfAvailableUpdates = 0;

    /*
    * The UpdateCheckerManager organizes all update check requests from the different lmh01 mods.
    *
    * When a new version is found the updateCheckerManager will tell the user and give him a download link
    *
    * New and Current Versions are stored here in Strings and will be compared when checking if an update is available.
    * */


    /**
     * Checks all registered lmh01 mods for updates. Register a new sub-mod fia {@link ChildModManager#registerChildMod(String, String, String, String, String)}
     */
    public static void checkForUpdates(){
        if(ChildModManager.areAllModsRegistered()){
            checkLMH01_libForUpdates();
            int modNumber = 1;
            for (int i = 0; i < ChildModManager.getModCount()*4; i= i+4){
                UpdateCheckerHelper.checkForUpdates(REGISTERED_MODS.get(i+3), REGISTERED_MODS.get(i), REGISTERED_MODS.get(i+2));
                modNumber++;
            }
        }else{
            WarningHelper.addWarning("Unable to check lmh01_lib and registered mods for updates: The registering process for child mods is not yet complete.");
        }
    }

    /**
     * Adds an entry to the UPDATE_AVAILABLE array list.
     * Do not use unless you know what you are doing!
     * @param modid The modid.
     * @param updateAvailable True if an update is available.
     */
    public static void addUpdateAvailable(String modid, boolean updateAvailable){
        if(updateAvailable){
            UPDATE_AVAILABLE.add(modid + "true");
        }else{
            UPDATE_AVAILABLE.add(modid + "false");
        }
    }

    /**
     * Adds an entry to the NEWEST_VERSION array list.
     * Do not use unless you know what you are doing!
     * @param newestVersion The newest version.
     */
    public static void addNewestVersion(String newestVersion){
        NEWEST_VERSION.add(newestVersion);
    }

    /**
     * Increases the number of available updates.
     * @param number The number of available updates.
     */
    public static void increaseNumberOfAvailableUpdates(int number){
        numberOfAvailableUpdates = numberOfAvailableUpdates + number;
    }

    /**
     * Returns the update available array list.
     * @return The array list.
     */
    public static ArrayList<String> getUpdateAvailableArrayList(){
        return UPDATE_AVAILABLE;
    }

    /**
     * Returns the newest version array list.
     * @return The array list.
     */
    public static ArrayList<String> getNewestVersionArrayList(){
        return NEWEST_VERSION;
    }

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
            }
        }.start();
    }

    /**
     * Returns true if a new LMH01_lib version is available.
     * @return Returns true if a new LMH01_lib version is available.
     */
    public static boolean isNewLMH01_libVersionAvailable(){
        return newLMH01_libVersionAvailable;
    }

    /**
     * Returns the newest available LMH01_lib version.
     * @return Returns the newest available LMH01_lib version.
     */
    public static String getNewestLMH01_libVersion(){
        return newestLMH01_libVersion;
    }

    /**
     * This function will print into the chat what updates are available
     */
    public static void printChatNotification(boolean calledFromEvents){
        if(ChildModManager.getModCount()!=0 && UpdateCheckerManager.numberOfAvailableUpdates!=0 ||newLMH01_libVersionAvailable){
            if(UpdateCheckerManager.numberOfAvailableUpdates==1){
                ChatHelper.sendTranslatedChatMessage("text.lmh01_lib.update_available", TextFormatting.GOLD);
            }else{
                ChatHelper.sendTranslatedChatMessage("text.lmh01_lib.updates_available", TextFormatting.GOLD);
            }
            if(newLMH01_libVersionAvailable){
                ChatHelper.sendChatMessage(TextFormatting.GOLD + "LMH01_lib (" + References.VERSION + "): " + TextFormatting.DARK_AQUA + UpdateCheckerManager.newestLMH01_libVersion, References.DOWNLOAD_URL, "tooltip.lmh01_lib.click_to_open_lmh01_lib_download_page");
            }
            ChildModManager.printSummary(true, true, false);
            ChatHelper.sendTranslatedChatMessage("text.lmh01_lib.click_version_hint", TextFormatting.GRAY);
        }else{
            if(!calledFromEvents){
                ChatHelper.sendTranslatedChatMessage("text.lmh01_lib.no_updates_available", TextFormatting.GREEN);
            }
        }
    }
    public static void printLMH01LibVersionToChat(CommandSource source){
        DebugHelper.sendDebugInformation("newestLMH01_libVersion: " + newestLMH01_libVersion,5);
        DebugHelper.sendDebugInformation("currentLMH01_libVersion: " + References.VERSION,5);
        if(References.VERSION.equals(newestLMH01_libVersion)){
            CommandHelper.sendCommandFeedback(TextFormatting.GOLD + "Current LMH01_lib version: " + TextFormatting.DARK_GREEN + References.VERSION, source, true);
        }else{
            source.sendFeedback(new StringTextComponent(TextFormatting.GOLD + "Current LMH01_lib version: " + TextFormatting.YELLOW + References.VERSION + TextFormatting.GOLD + "\nThis version is " + TextFormatting.RED + "Outdated!" + TextFormatting.GOLD + " Click this message to open the download page.").modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, References.DOWNLOAD_URL))).modifyStyle(style -> style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("tooltip.lmh01_lib.click_to_open_lmh01_lib_download_page")))), true);
        }
    }

}
