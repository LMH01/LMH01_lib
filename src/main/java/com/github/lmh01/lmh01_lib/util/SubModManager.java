package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.ChatHelper;
import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import net.minecraft.util.text.TextFormatting;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class SubModManager {
    public static ArrayList<String> registeredMods = new ArrayList<>();
    public static ArrayList<String> registeredAddons = new ArrayList<>();
    public static ArrayList<String> registeredModNames = new ArrayList<>();
    public static ArrayList<String> downloadURLs = new ArrayList<>();
    private static int modsCount = 0;
    private static int modAddonCount = 0;
    /*
    * This class is used for integration with the mods that depend on lmh01_lib
    * */

    /**
     * Use this function to register a new SubMod to LMH01_lib. When registering a new SubMod the mod will be
     * checked for updates and will be present in the loading summary. Other integrations might be added later.
     *
     * @param modid The modid of the mod that sends the register request
     * @param name The name of the mod that sends the register request
     * @param version The version of the mod that sends the register request
     * @param updateURL The updateURL of the mod that sends the register request
     * @param downloadURL The URL where the download is available
     */
    public static void registerSubMod(String modid, String name, String version, String updateURL, String downloadURL){
        modsCount++;
        DebugHelper.sendDebugInformation("Registering new SubMod: " + modid + ", " + name + ", " + version + ", " + updateURL, 2, 0);
        registeredMods.add(modid);
        registeredMods.add(name);
        registeredMods.add(version);
        registeredMods.add(updateURL);
        downloadURLs.add(modid + downloadURL);/*Modid is used to make it easier to sort*/
        /*This call is used to create a array list with just the mod names to make it easier to sort it later*/
        registeredModNames.add(name);
    }
    /**
     * Use this function to register a new ModAddon to LMH01_lib. When registering a new ModAddon it will be
     * present in the loading summary. Other integrations might be added later.
     *
     * @param name The name of the mod that sends the register request
     */
    public static void registerModAddon(String name){
        modAddonCount++;
        DebugHelper.sendDebugInformation("Registering new Addon: " + name, 2, 0);
        registeredAddons.add(name);
    }

    public static int getModCount(){
        return modsCount;
    }
    public static int getModAddonCount (){
        return modAddonCount;
    }

    /**
     * Prints a summary of installed mods into the console/chat. Also shows available updates.
     * @param ingame Set true when this is called from within the game
     * @param onlyShowNewVersions Set true when you only want to print new mod versions into the console/chat.
     * @param showModAddons Set true when you want to show the Addons
     */
    public static void printSummary(boolean ingame, boolean onlyShowNewVersions, boolean showModAddons){
        /*This part sorts the mods by aplhabet to display them in the correct order in the loading summary. Because
         * the ArrayList registeredModNames is in another order that registeredMods this code sorts them to be
         * displayed correctly*/
        Collections.sort(SubModManager.registeredModNames);
        Collections.sort(UpdateCheckerManager.newestVersion);
        Collections.sort(SubModManager.downloadURLs);
        Collections.sort(UpdateCheckerManager.updateAvailable);
        for (int i = 0; i < SubModManager.getModCount(); i++){
            for (int n = 0; n < SubModManager.getModCount()*4; n= n+4){
                if(SubModManager.registeredModNames.get(i).equals(SubModManager.registeredMods.get(n+1))){
                    if(UpdateCheckerManager.updateAvailable.get(i).contains("true")){
                        if(ingame){
                            //TODO Send to chat as TranslationTextcomponent.
                            String tempStorageModName = SubModManager.registeredModNames.get(i) + " (" + SubModManager.registeredMods.get(n+2) + "): ";
                            String tempStorageNewVersion = "Update available - " + UpdateCheckerManager.newestVersion.get(i);
                            //TODO Make version number clickable to get to the update page
                            tempStorageModName = tempStorageModName.replace(SubModManager.registeredMods.get(n),"");
                            tempStorageNewVersion = tempStorageNewVersion.replace(SubModManager.registeredMods.get(n),"");
                            ChatHelper.sendChatMessage(TextFormatting.GOLD + tempStorageModName + TextFormatting.DARK_AQUA + tempStorageNewVersion + TextFormatting.DARK_AQUA);
                        }else{
                            String tempStorage = SubModManager.registeredModNames.get(i) + " (" + SubModManager.registeredMods.get(n+2) + "): Update available - " + UpdateCheckerManager.newestVersion.get(i) + "; Download: " + SubModManager.downloadURLs.get(i).replace(SubModManager.registeredMods.get(i), "");
                            /*When printing this into the chat the new version number will will be clickable to open the update side*/
                            tempStorage = tempStorage.replace(SubModManager.registeredMods.get(n),"");
                            DebugHelper.sendDebugInformation(tempStorage, 1, 0, References.MODID);
                        }
                    }else if(!onlyShowNewVersions){
                        if(ingame){
                            ChatHelper.sendChatMessage(TextFormatting.GOLD + SubModManager.registeredModNames.get(i) + " (" + SubModManager.registeredMods.get(n+2) + "): " + TextFormatting.DARK_GREEN + "Installed");
                        }else{
                            DebugHelper.sendDebugInformation(SubModManager.registeredModNames.get(i) + " (" + SubModManager.registeredMods.get(n+2) + "): Installed", 1, 0, References.MODID);
                        }
                    }
                }
            }

        }
        if(SubModManager.getModAddonCount() != 0 && showModAddons){
            Collections.sort(SubModManager.registeredAddons);
            if(ingame){
                for (int i = 0; i < SubModManager.getModAddonCount(); i++){
                    //TODO Add TextFormatting
                    ChatHelper.sendChatMessage("Addons: ");
                    ChatHelper.sendChatMessage(SubModManager.registeredAddons.get(i));
                }
            }else{
                DebugHelper.sendDebugInformation("Addons:", 1, 0, References.MODID);
                for (int i = 0; i < SubModManager.getModAddonCount(); i++){
                    DebugHelper.sendDebugInformation(SubModManager.registeredAddons.get(i), 1, 0, References.MODID);
                }
            }
        }
    }

    /**
     * This function will print into the chat what updates are available
     */
    public static void printChatNotification(){
        ChatHelper.sendChatMessage(TextFormatting.GOLD + "Updates available:");
        SubModManager.printSummary(true, true, false);
    }
}
