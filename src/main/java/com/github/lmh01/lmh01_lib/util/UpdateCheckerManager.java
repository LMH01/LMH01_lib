package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.helpers.UpdateCheckerHelper;
import com.github.lmh01.lmh01_lib.helpers.WarningHelper;
import com.github.lmh01.lmh01_lib.helpers.WebHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class UpdateCheckerManager {
    public static ArrayList<String> updateAvailable = new ArrayList<>();
    public static ArrayList<String> newestVersion = new ArrayList<>();
    public static final CountDownLatch waitForUpdatesFinished = new CountDownLatch(SubModManager.getModCount()+1);
    public static boolean newLMH01_libVersionAvailable = false;
    public static String newestLMH01_libVersion = "";

    /*
    * The UpdateCheckerManager organizes all update check requests from the different lmh01 mods.
    *
    * When a new version is found the updateCheckerManager will tell the user and give him a download link
    *
    * New and Current Versions are stored here in Strings and will be compared when checking if an update is available.
    * */


    /**
     * Checks all registered lmh01 mods for updates. Register a new sub-mod fia {@link SubModManager#registerSubMod(String, String, String, String, String)}
     */
    public static void checkForUpdates(){
        checkLMH01_libForUpdates();
        int modNumber = 1;
        for (int i = 0; i < SubModManager.getModCount()*4; i= i+4){
            DebugHelper.sendDebugInformation("Checking for updates with parameters: updateURL: " + SubModManager.registeredMods.get(i+3) +
                    " modid: " + SubModManager.registeredMods.get(i) + " currentVersion: " + SubModManager.registeredMods.get(i+2), 2, 0);
            UpdateCheckerHelper.checkForUpdates(SubModManager.registeredMods.get(i+3), SubModManager.registeredMods.get(i), SubModManager.registeredMods.get(i+2));
            modNumber++;

        }
    }
    /*Shows Loading Summary when all update checkers are done*/
    public static Runnable runnableWaitForUpdatesFinishedAndSendLoadingSummary = () -> {
        try {
            waitForUpdatesFinished.await();
            LoadingSummary.showLoadingSummary();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    /**
     * Checks LMH01_lib for updates
     */
    private static void checkLMH01_libForUpdates(){
        new Thread("LMH01_lib update checker"){
            public void run(){
                String newestVersion = WebHelper.getContentFromURL(References.UPDATE_URL);
                if(!newestVersion.equals(References.VERSION)){
                    newestLMH01_libVersion = newestVersion;
                    newLMH01_libVersionAvailable = true;
                }
                waitForUpdatesFinished.countDown();
            }
        }.start();
    }

}
