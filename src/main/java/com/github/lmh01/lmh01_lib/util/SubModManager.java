package com.github.lmh01.lmh01_lib.util;

public class SubModManager {
    public static boolean AllOfEverythingInstalled = false;
    public static boolean TrekCraftInstalled = false;
    private static int ModsCount = 0;
    private static int ModAddonCount = 0;
    /*
    * This class is used for integration with the mods that depend on lmh01_lib
    * This class also registers if
    * */
    public static void registerSubMods(String ModID){
        if(ModID.equals("trekcraft")){
            SubModManager.TrekCraftInstalled = true;
        }else if(ModID.equals("aoe")){
            SubModManager.AllOfEverythingInstalled = true;
        }
        ModsCount++;
    }
    public static void registerSubModAddons(String ModID){
        ModAddonCount++;
    }

}
