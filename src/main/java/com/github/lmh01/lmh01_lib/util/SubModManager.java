package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.ChatHelper;
import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.helpers.WarningHelper;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Collections;

public class SubModManager {
    private static final ArrayList<String> REGISTERED_MODS = new ArrayList<>();
    private static final ArrayList<String> REGISTERED_ADDONS = new ArrayList<>();
    private static final ArrayList<String> REGISTERED_MOD_NAMES = new ArrayList<>();
    private static final ArrayList<String> DOWNLOAD_URLS = new ArrayList<>();
    private static final ArrayList<String> NEWEST_VERSION = UpdateCheckerManager.getNewestVersionArrayList();
    private static final ArrayList<String> UPDATE_AVAILABLE = UpdateCheckerManager.getUpdateAvailableArrayList();
    private static int modsCount = 0;
    private static int modAddonCount = 0;
    private static boolean allModsRegistered = false;
    /*
    * This class is used for integration with the mods that depend on lmh01_lib
    * */

    /**
     * Use this function to register a new SubMod to LMH01_lib. When registering a new SubMod the mod will be
     * checked for updates and will be present in the loading summary. Other integrations might be added later. Only works until post-init (InterModProcessEvent) is started.
     *
     * @param modid The modid of the mod that sends the register request
     * @param name The name of the mod that sends the register request
     * @param version The version of the mod that sends the register request
     * @param updateURL The updateURL of the mod that sends the register request
     * @param downloadURL The URL where the download is available
     */
    public static void registerSubMod(String modid, String name, String version, String updateURL, String downloadURL){
        if(!allModsRegistered){
            modsCount++;
            DebugHelper.sendDebugInformation("Registering new SubMod: modid: " + modid + ", name: " + name + ", version: " + version + ", updateURL: " + updateURL + ", downloadURL: " + downloadURL, 4);
            REGISTERED_MODS.add(modid);
            REGISTERED_MODS.add(name);
            REGISTERED_MODS.add(version);
            REGISTERED_MODS.add(updateURL);
            DOWNLOAD_URLS.add(modid + downloadURL);/*Modid is used to make it easier to sort*/
            /*This call is used to create a array list with just the mod names to make it easier to sort it later*/
            REGISTERED_MOD_NAMES.add(name);
        }else{
            WarningHelper.addWarning(modid + " tried to register a mod to lmh01_lib after InterModProcessEvent has been started.");
        }
    }

    /**
     * Use this function to register a new ModAddon to LMH01_lib. When registering a new ModAddon it will be
     * present in the loading summary. Other integrations might be added later.
     *
     * @param name The name of the mod that sends the register request
     */
    public static void registerModAddon(String name){
        modAddonCount++;
        DebugHelper.sendDebugInformation("Registering new Addon: name: " + name, 4);
        REGISTERED_ADDONS.add(name);
    }

    /**
     *  Call this function to prevent future mods from registering to LMH01_lib.
     *  Useful to stop mods from registering when Minecraft loading is complete to prevent errors.
     */
    public static void setAllModsRegistered(){
        allModsRegistered = true;
    }

    /**
     * Returns if all mods are registered. When true no more mods can be registered to lmh01_lib.
     * @return Returns if all mods are registered.
     */
    public static boolean areAllModsRegistered(){
        return allModsRegistered;
    }

    /**
     * Returns the amount of mods that are registered to LMH01_lib.
     * @return The amount of registered mods.
     */
    public static int getModCount(){
        return modsCount;
    }

    /**
     * Returns the amount of mods addons that are registered to LMH01_lib.
     * @return The amount of registered mods addons.
     */
    public static int getModAddonCount (){
        return modAddonCount;
    }

    /**
     * Returns the mods array list.
     * @return The array list.
     */
    public static ArrayList<String> getRegisteredModsArrayList(){
        return REGISTERED_MODS;
    }

    /**
     * Prints a summary of installed mods into the console/chat. Also shows available updates.
     * @param ingame Set true when this is called from within the game.
     * @param onlyShowNewVersions Set true when you only want to print new mod versions into the console/chat.
     * @param showModAddons Set true when you want to show the Addons.
     */
    public static void printSummary(boolean ingame, boolean onlyShowNewVersions, boolean showModAddons){
        /*This part sorts the mods by aplhabet to display them in the correct order in the loading summary. Because
         * the ArrayList registeredModNames is in another order that registeredMods this code sorts them to be
         * displayed correctly*/
        Collections.sort(REGISTERED_MOD_NAMES);
        Collections.sort(NEWEST_VERSION);
        Collections.sort(DOWNLOAD_URLS);
        Collections.sort(UPDATE_AVAILABLE);
        for (int i = 0; i < getModCount(); i++){
            for (int n = 0; n < getModCount()*4; n= n+4){
                if(REGISTERED_MOD_NAMES.get(i).equals(REGISTERED_MODS.get(n+1))){
                    if(UPDATE_AVAILABLE.get(i).contains("true")){
                        if(ingame){
                            String tempStorageModName = REGISTERED_MOD_NAMES.get(i) + " (" + REGISTERED_MODS.get(n+2) + "): ";
                            String tempStorageNewVersion = NEWEST_VERSION.get(i);
                            String tempStorageDownloadURL = DOWNLOAD_URLS.get(i);
                            tempStorageModName = tempStorageModName.replace(REGISTERED_MODS.get(n),"");
                            tempStorageNewVersion = tempStorageNewVersion.replace(REGISTERED_MODS.get(n),"");
                            final String tempStorageDownloadURLToUse = tempStorageDownloadURL.replace(REGISTERED_MODS.get(n), "");
                            ChatHelper.sendChatMessage(TextFormatting.GOLD + tempStorageModName + TextFormatting.DARK_AQUA + tempStorageNewVersion, tempStorageDownloadURLToUse, "tooltip.click_to_open_mod_download_website");
                        }else{
                            String tempStorage = REGISTERED_MOD_NAMES.get(i) + " (" + REGISTERED_MODS.get(n+2) + "): Update available - " + NEWEST_VERSION.get(i) + "; Download: " + DOWNLOAD_URLS.get(i).replace(REGISTERED_MODS.get(i), "");
                            /*When printing this into the chat the new version number will will be clickable to open the update side*/
                            tempStorage = tempStorage.replace(REGISTERED_MODS.get(n),"");
                            DebugHelper.sendDebugInformation(tempStorage, 5);
                        }
                    }else if(!onlyShowNewVersions){
                        if(ingame){
                            String tempStorageDownloadURL = DOWNLOAD_URLS.get(i);
                            final String tempStorageDownloadURLToUse = tempStorageDownloadURL.replace(REGISTERED_MODS.get(n), "");
                            ChatHelper.sendChatMessage(TextFormatting.GOLD + REGISTERED_MOD_NAMES.get(i) + " (" + REGISTERED_MODS.get(n+2) + "): " + TextFormatting.DARK_GREEN + "Installed", tempStorageDownloadURLToUse, "tooltip.click_to_open_mod_download_website");
                        }else{
                            DebugHelper.sendDebugInformation(REGISTERED_MOD_NAMES.get(i) + " (" + REGISTERED_MODS.get(n+2) + "): Installed", 5);
                        }
                    }
                }
            }

        }
        if(SubModManager.getModAddonCount() != 0 && showModAddons){
            Collections.sort(SubModManager.REGISTERED_ADDONS);
            if(ingame){
                ChatHelper.sendChatMessage(TextFormatting.DARK_GREEN + "Addons: ");
                for (int i = 0; i < SubModManager.getModAddonCount(); i++){
                    ChatHelper.sendChatMessage(TextFormatting.GOLD + SubModManager.REGISTERED_ADDONS.get(i));
                }
            }else{
                DebugHelper.sendDebugInformation("Addons:", 5);
                for (int i = 0; i < SubModManager.getModAddonCount(); i++){
                    DebugHelper.sendDebugInformation(SubModManager.REGISTERED_ADDONS.get(i), 5);
                }
            }
        }
    }

}
