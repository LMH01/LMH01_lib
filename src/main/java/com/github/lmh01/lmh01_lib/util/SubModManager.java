package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.DebugHelper;

import java.net.URL;
import java.util.ArrayList;

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

}
