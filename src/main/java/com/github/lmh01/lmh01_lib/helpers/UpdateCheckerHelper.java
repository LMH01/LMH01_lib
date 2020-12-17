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
                DebugHelper.sendDebugInformation(modid + ": Searching for updates...", 2, 0, modid);
                String latestVersion =  WebHelper.getContentFromURL(updateURL);
                String latestVersionWithModid = modid + latestVersion;

                if (!currentModVersion.equals(latestVersion)){
                    DebugHelper.sendDebugInformation("Update check complete for " + modid + " using url: " + updateURL + ": New update found: " + latestVersion, 2, 0);
                    UpdateCheckerManager.updateAvailable.add("true");
                    UpdateCheckerManager.newestVersion.add(latestVersionWithModid);
                }else{
                    DebugHelper.sendDebugInformation("Update check complete for " + modid + ": No update available", 2, 0);
                    UpdateCheckerManager.updateAvailable.add("false");
                    UpdateCheckerManager.newestVersion.add(currentModVersion);
                }
                UpdateCheckerManager.waitForUpdatesFinished.countDown();
                timesRun++;

                /*try {
                    DebugHelper.sendDebugInformation(ModID + ": Searching for updates... (timesRun: " + timesRun + ")", 2, 0, ModID);
                    String latestVersion = WebHelper.getContentFromURL(updateURL);
                    newVersions.add(latestVersion);


                    if (!currentModVersion.equals(newVersions.get(timesRun))){
                        DebugHelper.sendDebugInformation("Update check complete for " + ModID + " using url: " + updateURL + ": New update found: " + newVersions.get(timesRun), 2, 0);
                        UpdateCheckerManager.updateAvailable.add("true");
                        UpdateCheckerManager.newestVersion.add(newVersions.get(timesRun));
                    }else{
                        DebugHelper.sendDebugInformation("Update check complete for " + ModID + ": No update available", 2, 0);
                        UpdateCheckerManager.updateAvailable.add("false");
                        UpdateCheckerManager.newestVersion.add(currentModVersion);
                    }
                    UpdateCheckerManager.waitForUpdatesFinished.countDown();
                    timesRun++;
                } catch (MalformedURLException e) {
                    ErrorHelper.addError("Update check failed: The URL is invalid.", ModID);
                    DebugHelper.sendDebugInformation("Stacktrace: ", 4, 0);
                    UpdateCheckerManager.updateAvailable.add("error");
                    e.printStackTrace();
                    UpdateCheckerManager.waitForUpdatesFinished.countDown();
                    timesRun++;
                } catch (IOException e) {
                    ErrorHelper.addError("Update check failed: Connection error.", ModID);
                    DebugHelper.sendDebugInformation("Stacktrace: ", 4, 0);
                    UpdateCheckerManager.updateAvailable.add("error");
                    e.printStackTrace();
                    UpdateCheckerManager.waitForUpdatesFinished.countDown();
                    timesRun++;
                }*/
            }

        }.start();
    }
}
