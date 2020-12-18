package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.SubModManager;
import com.github.lmh01.lmh01_lib.util.UpdateCheckerManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class UpdateCheckerHelper {
    public static int timesRun = 0;

    /**
     * Checks a specified URL for a version sting and compares it to the current version. Do not call this function unless
     * you know what you are doing. This function may only be called by the {@link UpdateCheckerManager}.
     * @param updateURL The updateURL
     * @param modid The modid
     * @param currentModVersion Current mod version
     */
    public static void checkForUpdates(String updateURL, String modid, String currentModVersion){
        new Thread("UpdateChecker: " + modid){
            public void run(){
                /*Modid is beeing added to the Arrays to make it possible to sort them later correctly*/
                DebugHelper.sendDebugInformation(modid + ": Searching for updates...", 2, 0, modid);
                String latestVersion =  WebHelper.getContentFromURL(updateURL);
                String latestVersionWithModid = modid + latestVersion;
                String currentVersionWithModid = modid + currentModVersion;

                if (!currentModVersion.equals(latestVersion)){
                    DebugHelper.sendDebugInformation("Update check complete for " + modid + " using url: " + updateURL + ": New update found: " + latestVersion, 2, 0);
                    UpdateCheckerManager.updateAvailable.add(modid + "true");
                    UpdateCheckerManager.newestVersion.add(latestVersionWithModid);
                }else{
                    DebugHelper.sendDebugInformation("Update check complete for " + modid + ": No update available", 2, 0);
                    UpdateCheckerManager.updateAvailable.add(modid + "false");
                    UpdateCheckerManager.newestVersion.add(currentVersionWithModid);
                }
                UpdateCheckerManager.waitForUpdatesFinished.countDown();
                timesRun++;
            }

        }.start();
    }
}
