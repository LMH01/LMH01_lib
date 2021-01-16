package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.ChatHelper;
import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import java.util.ArrayList;
import java.util.Collections;

public class SubModManager {
    public static final ArrayList<String> REGISTERED_MODS = new ArrayList<>();
    public static final ArrayList<String> REGISTERED_ADDONS = new ArrayList<>();
    public static final ArrayList<String> REGISTERED_MOD_NAMES = new ArrayList<>();
    public static final ArrayList<String> DOWNLOAD_URLS = new ArrayList<>();
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
        DebugHelper.sendDebugInformation("Registering new SubMod: modid: " + modid + ", name: " + name + ", version: " + version + ", updateURL: " + updateURL + ", downloadURL: " + downloadURL, 4, 0);
        REGISTERED_MODS.add(modid);
        REGISTERED_MODS.add(name);
        REGISTERED_MODS.add(version);
        REGISTERED_MODS.add(updateURL);
        DOWNLOAD_URLS.add(modid + downloadURL);/*Modid is used to make it easier to sort*/
        /*This call is used to create a array list with just the mod names to make it easier to sort it later*/
        REGISTERED_MOD_NAMES.add(name);
    }
    /**
     * Use this function to register a new ModAddon to LMH01_lib. When registering a new ModAddon it will be
     * present in the loading summary. Other integrations might be added later.
     *
     * @param name The name of the mod that sends the register request
     */
    public static void registerModAddon(String name){
        modAddonCount++;
        DebugHelper.sendDebugInformation("Registering new Addon: name: " + name, 4, 0);
        REGISTERED_ADDONS.add(name);
    }

    public static int getModCount(){
        return modsCount;
    }
    public static int getModAddonCount (){
        return modAddonCount;
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
        Collections.sort(SubModManager.REGISTERED_MOD_NAMES);
        Collections.sort(UpdateCheckerManager.NEWEST_VERSION);
        Collections.sort(SubModManager.DOWNLOAD_URLS);
        Collections.sort(UpdateCheckerManager.UPDATE_AVAILABLE);
        for (int i = 0; i < SubModManager.getModCount(); i++){
            for (int n = 0; n < SubModManager.getModCount()*4; n= n+4){
                if(SubModManager.REGISTERED_MOD_NAMES.get(i).equals(SubModManager.REGISTERED_MODS.get(n+1))){
                    if(UpdateCheckerManager.UPDATE_AVAILABLE.get(i).contains("true")){
                        if(ingame){
                            String tempStorageModName = SubModManager.REGISTERED_MOD_NAMES.get(i) + " (" + SubModManager.REGISTERED_MODS.get(n+2) + "): ";
                            String tempStorageNewVersion = UpdateCheckerManager.NEWEST_VERSION.get(i);
                            String tempStorageDownloadURL = SubModManager.DOWNLOAD_URLS.get(i);
                            tempStorageModName = tempStorageModName.replace(SubModManager.REGISTERED_MODS.get(n),"");
                            tempStorageNewVersion = tempStorageNewVersion.replace(SubModManager.REGISTERED_MODS.get(n),"");
                            final String tempStorageDownloadURLToUse = tempStorageDownloadURL.replace(SubModManager.REGISTERED_MODS.get(n), "");

                            Minecraft.getInstance().player.sendMessage(new StringTextComponent(TextFormatting.GOLD + tempStorageModName + TextFormatting.DARK_AQUA + tempStorageNewVersion).modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, tempStorageDownloadURLToUse))).modifyStyle(style -> style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("tooltip.click_to_open_mod_download_website")))), Minecraft.getInstance().player.getUniqueID());
                        }else{
                            String tempStorage = SubModManager.REGISTERED_MOD_NAMES.get(i) + " (" + SubModManager.REGISTERED_MODS.get(n+2) + "): Update available - " + UpdateCheckerManager.NEWEST_VERSION.get(i) + "; Download: " + SubModManager.DOWNLOAD_URLS.get(i).replace(SubModManager.REGISTERED_MODS.get(i), "");
                            /*When printing this into the chat the new version number will will be clickable to open the update side*/
                            tempStorage = tempStorage.replace(SubModManager.REGISTERED_MODS.get(n),"");
                            DebugHelper.sendDebugInformation(tempStorage, 5);
                        }
                    }else if(!onlyShowNewVersions){
                        if(ingame){
                            String tempStorageDownloadURL = SubModManager.DOWNLOAD_URLS.get(i);
                            final String tempStorageDownloadURLToUse = tempStorageDownloadURL.replace(SubModManager.REGISTERED_MODS.get(n), "");
                            Minecraft.getInstance().player.sendMessage(new StringTextComponent(TextFormatting.GOLD + SubModManager.REGISTERED_MOD_NAMES.get(i) + " (" + SubModManager.REGISTERED_MODS.get(n+2) + "): " + TextFormatting.DARK_GREEN + "Installed").modifyStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, tempStorageDownloadURLToUse))).modifyStyle(style -> style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("tooltip.click_to_open_mod_download_website")))), Minecraft.getInstance().player.getUniqueID());
                        }else{
                            DebugHelper.sendDebugInformation(SubModManager.REGISTERED_MOD_NAMES.get(i) + " (" + SubModManager.REGISTERED_MODS.get(n+2) + "): Installed", 5);
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
                    //TODO Add TextFormatting
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
