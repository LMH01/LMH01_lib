package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.helpers.ErrorHelper;
import com.github.lmh01.lmh01_lib.helpers.WarningHelper;

public class LoadingSummary {
   /*
     * Uses the information got from UpdateChecker to determine if a mod is installed.
     * Also shows the currently installed mod version and if a update is available + the newest version in that case
     * */

    /**
     * Prints the loading summary in the console.
     */
    public static void showLoadingSummary() {
        showStartPart();
        showWaringsAndErrorsPart();
        showLMH01_libVersion();
        showNews();
        if(ChildModManager.getModCount() == UpdateCheckerManager.getUpdateAvailableArrayList().size()){
            showComponentsPart();
        }else{
            WarningHelper.addWarning("Can't show information about LMH01 Mods in loading summary now: Update Checks are not complete yet.");
        }
    }

    private static void showStartPart() {
        send("----------Starting Report----------");
        if (WarningHelper.getNumberOfWarnings() > 0 && ErrorHelper.getNumberOfErrors() > 0) {
            send("LMH01_lib has been succesfully Installed with " + WarningHelper.getNumberOfWarnings() + " Warnings and " + ErrorHelper.getNumberOfErrors() + " Errors!");
        } else if (WarningHelper.getNumberOfWarnings() > 0 && ErrorHelper.getNumberOfErrors() == 0) {
            send("LMH01_lib has been succesfully Installed with " + WarningHelper.getNumberOfWarnings() + " Warnings!");
        } else if (WarningHelper.getNumberOfWarnings() == 0 && ErrorHelper.getNumberOfErrors() > 0) {
            send("LMH01_lib has been succesfully Installed with " + ErrorHelper.getNumberOfErrors() + " Errors!");
        } else {
            if (ChildModManager.getModCount() == 1 && ChildModManager.getModAddonCount() == 1) {
                send("LMH01_lib has been succesfully Installed with " + ChildModManager.getModCount() + " Mod and " + ChildModManager.getModAddonCount() + " Addon!");
            } else if (ChildModManager.getModCount() > 1 && ChildModManager.getModAddonCount() == 1) {
                send("LMH01_lib has been succesfully Installed with " + ChildModManager.getModCount() + " Mods and " + ChildModManager.getModAddonCount() + " Addon!");
            } else if (ChildModManager.getModCount() > 1 && ChildModManager.getModAddonCount() > 1) {
                send("LMH01_lib has been succesfully Installed with " + ChildModManager.getModCount() + " Mods and " + ChildModManager.getModAddonCount() + " Addons!");
            } else if (ChildModManager.getModCount() == 1 && ChildModManager.getModAddonCount() > 1) {
                send("LMH01_lib has been succesfully Installed with " + ChildModManager.getModCount() + " Mod and " + ChildModManager.getModAddonCount() + " Addons!");
            } else if (ChildModManager.getModCount() == 0 && ChildModManager.getModAddonCount() == 0) {
                send("LMH01_lib has been succesfully Installed with " + ChildModManager.getModCount() + " Mods and " + ChildModManager.getModAddonCount() + " Addons!");
            } else if (ChildModManager.getModCount() == 1 && ChildModManager.getModAddonCount() == 0) {
                send("LMH01_lib has been succesfully Installed with " + ChildModManager.getModCount() + " Mod and " + ChildModManager.getModAddonCount() + " Addons!");
            } else if (ChildModManager.getModCount() == 0 && ChildModManager.getModAddonCount() == 1) {
                send("LMH01_lib has been succesfully Installed with " + ChildModManager.getModCount() + " Mods and " + ChildModManager.getModAddonCount() + " Addon!");
            } else if (ChildModManager.getModCount() > 1 && ChildModManager.getModAddonCount() == 0) {
                send("LMH01_lib has been succesfully Installed with " + ChildModManager.getModCount() + " Mods and " + ChildModManager.getModAddonCount() + " Addons!");
            } else {
                send("LMH01_lib Mod has been succesfully Installed with " + ChildModManager.getModCount() + " Mod and " + ChildModManager.getModAddonCount() + " Addon!");
            }
        }
    }

    private static void showWaringsAndErrorsPart() {
        /*Dieser Teil zeigt die Warnungen an*/
        if (WarningHelper.getNumberOfWarnings() > 0) {
            send(WarningHelper.getListOfWarningsAsString());
        }

        if (ErrorHelper.getNumberOfErrors() > 0) {
            send(ErrorHelper.getListOfErrorsAsString());
        }
    }
    private static void showMainPart(){
        send("-Loading Summary-");

    }

    private static void showComponentsPart() {
        /*
        * Shows the installed mods and their Version + newVersion (See UpdateCheckerManager)
        * */


        send("LMH01 Mods: ");
        if(ChildModManager.getModCount()!=0){
            ChildModManager.printSummary(false, false, true);
        }else{
            send("No mods installed");
        }

    }
    private static void showLMH01_libVersion(){
        if(UpdateCheckerManager.isNewLMH01_libVersionAvailable()){
            send("An update for LMH01_lib is available: " + UpdateCheckerManager.getNewestLMH01_libVersion() + " Download - " + References.DOWNLOAD_URL);
        }else{
            send("Version: " + References.VERSION);
        }

    }
    private static void showNews(){
        if(NewsHandler.areNewsAvailable()){
            send("News: " + NewsHandler.getNews());
        }
    }
    /**
     * Sends a information into the console with less parameters than {@link DebugHelper#sendDebugInformation(String, int, String)}
     * @param message The message sent
     */
    private static void send (String message){
        DebugHelper.sendDebugInformation(message, 5);
    }
}

