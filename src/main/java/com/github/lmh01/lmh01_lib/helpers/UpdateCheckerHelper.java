package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.SubModManager;
import com.github.lmh01.lmh01_lib.util.UpdateCheckerManager;

public class UpdateCheckerHelper {

    /**
     * Checks a specified URL for a version sting and compares it to the current version. Do not call this function unless
     * you know what you are doing. This function may only be called by the {@link UpdateCheckerManager}.
     * If you want to check your mod for updates, register your mod fia {@link SubModManager#registerSubMod(String, String, String, String, String)}.
     * @param updateURL The updateURL
     * @param modid The modid
     * @param currentModVersion Current mod version
     */
    public static void checkForUpdates(String updateURL, String modid, String currentModVersion){
        new Thread("UpdateChecker: " + modid){
            public void run(){
                /*Modid is beeing added to the Arrays to make it possible to sort them later correctly*/
                DebugHelper.sendDebugInformation(modid + ": Searching for updates...", 5, 0, modid);
                String latestVersion =  WebHelper.getContentFromURL(updateURL);
                String latestVersionWithModid = modid + latestVersion;
                String currentVersionWithModid = modid + currentModVersion;

                if (!currentModVersion.equals(latestVersion)){
                    DebugHelper.sendDebugInformation("Update check complete for " + modid + " using url: " + updateURL + ": New update found: " + latestVersion, 4, 0);
                    UpdateCheckerManager.addUpdateAvailable(modid, true);
                    UpdateCheckerManager.addNewestVersion(latestVersionWithModid);
                    UpdateCheckerManager.increaseNumberOfAvailableUpdates(1);
                }else{
                    DebugHelper.sendDebugInformation("Update check complete for " + modid + ": No update available", 4, 0);
                    UpdateCheckerManager.addUpdateAvailable(modid, false);
                    UpdateCheckerManager.addNewestVersion(currentVersionWithModid);
                }
                UpdateCheckerManager.countDownWaitForUpdatesFinished();
            }

        }.start();
    }
}
