package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.UpdateCheckerManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class UpdateCheckerHelper {
    public static void checkForUpdates(String updateURL, String ModID, String currentModVersion){
        new Thread("UpdateChecker: " + ModID){
            public void run(){
                try {
                    URL url = new URL(updateURL);
                    DebugHelper.sendDebugInformation(ModID + ": Searching for updates...", 2, 0, ModID);
                    Scanner scanner = new Scanner(url.openStream());
                    String LatestVersion = scanner.nextLine();
                    scanner.close();

                    if (!currentModVersion.equals(LatestVersion)){
                       if(ModID.equals("lmh01_lib")){
                           UpdateCheckerManager.LMH01_libNewestVersion = LatestVersion;
                       }else if(ModID.equals("trekcraft")){
                           UpdateCheckerManager.TrekCraftNewestVersion = LatestVersion;
                       }else if(ModID.equals("aoe")){
                           UpdateCheckerManager.AllOfEverythingNewestVersion = LatestVersion;
                       }else{
                           ErrorHelper.addError("Update check failed: The ModID is invalid.", ModID);
                       }
                    }
                } catch (MalformedURLException e) {
                    ErrorHelper.addError("Update check failed: The URL is invalid.", ModID);
                    DebugHelper.sendDebugInformation("Stacktrace: ", 4, 0);
                    e.printStackTrace();
                } catch (IOException e) {
                    ErrorHelper.addError("Update check failed: Connection error.", ModID);
                    DebugHelper.sendDebugInformation("Stacktrace: ", 4, 0);
                    e.printStackTrace();
                }

            };

        }.start();
    }
}
