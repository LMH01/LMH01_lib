package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.UpdateCheckerHelper;

public class UpdateCheckerManager {
    public static String LMH01_libNewestVersion = "not yet searched";
    public static String TrekCraftNewestVersion = "not yet searched";
    public static String AllOfEverythingNewestVersion = "not yet searched";
    public static String LMH01_libCurrentVersion = "not available";
    public static String TrekCraftCurrentVersion = "not available";
    public static String AllOfEverythingCurrentVersion = "not available";
    private static boolean LMH01_libUpdateCheckStarted = false;
    private static boolean TrekCraftUpdateCheckStarted = false;
    private static boolean AllOfEverythingUpdateCheckStarted = false;

    /*
    * The UpdateCheckerManager organizes all update check requests from the different lmh01 mods.
    * Using checkForUpdates method will tell the manager that the specified mod is beeing loaded and that it should
    * be included in the final updateCheckerSummary.
    *
    * When a new version is found the updateCheckerManager will give the option to use /lmh01 update which will update
    * all outdated lmh01 mods.
    *
    * New and Current Versions are stored here in Strings and will be compared when checking if an update is available.
    * */


    /**
     * Sends a update check request to the UpdateCheckerManager. Tells the manager that said mod is being loaded and
     * that it should be included in the update checker summary.
     * @param updateURL The URL where the newest version string can be found
     * @param ModID The modid from the mod which requests the update check
     * @param currentModVersion The current version which the mod is running
     */
    public static void checkForUpdates(String updateURL, String ModID, String currentModVersion){
        if(ModID.equals("lmh01_lib")){
            LMH01_libUpdateCheckStarted = true;
            LMH01_libCurrentVersion = currentModVersion;
        }else if(ModID.equals("trekcraft")){
            TrekCraftUpdateCheckStarted = true;
            TrekCraftCurrentVersion = currentModVersion;
            SubModManager.TrekCraftInstalled = true;
        }else if(ModID.equals("aoe")){
            AllOfEverythingUpdateCheckStarted = true;
            AllOfEverythingCurrentVersion = currentModVersion;
            SubModManager.AllOfEverythingInstalled = true;
        }
        UpdateCheckerHelper.checkForUpdates(updateURL, ModID, currentModVersion);
    }

}
