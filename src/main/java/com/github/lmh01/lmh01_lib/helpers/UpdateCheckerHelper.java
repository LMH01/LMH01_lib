package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.SubModManager;
import com.github.lmh01.lmh01_lib.util.UpdateCheckerManager;

public class UpdateCheckerHelper {
    public static int timesRun = 0;

    /**
     * Checks a specified URL for a version sting and compares it to the current version. Do not call this function unless
     * you know what you are doing. This function may only be called by the {@link UpdateCheckerManager}.
     * If you want to check your mod for update register your mod fia {@link SubModManager#registerSubMod(String, String, String, String, String)}.
     * @param updateURL The updateURL
     * @param modid The modid
     * @param currentModVersion Current mod version
     */
    public static void checkForUpdates(String updateURL, String modid, String currentModVersion){
        new Thread("UpdateChecker: " + modid){
            public void run(){
                /*Modid is beeing added to the Arrays to make it possible to sort them later correctly*/
                DebugHelper.sendDebugInformation(modid + ": Searching for updates...", 4, 0, modid);
                String latestVersion =  WebHelper.getContentFromURL(updateURL);
                String latestVersionWithModid = modid + latestVersion;
                String currentVersionWithModid = modid + currentModVersion;

                if (!currentModVersion.equals(latestVersion)){
                    DebugHelper.sendDebugInformation("Update check complete for " + modid + " using url: " + updateURL + ": New update found: " + latestVersion, 4, 0);
                    UpdateCheckerManager.UPDATE_AVAILABLE.add(modid + "true");
                    UpdateCheckerManager.NEWEST_VERSION.add(latestVersionWithModid);
                    UpdateCheckerManager.numberOfAvailableUpdates++;
                }else{
                    DebugHelper.sendDebugInformation("Update check complete for " + modid + ": No update available", 4, 0);
                    UpdateCheckerManager.UPDATE_AVAILABLE.add(modid + "false");
                    UpdateCheckerManager.NEWEST_VERSION.add(currentVersionWithModid);
                }
                UpdateCheckerManager.WAIT_FOR_UPDATES_FINISHED.countDown();
                timesRun++;
            }

        }.start();
    }
}
