package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.helpers.ErrorHelper;
import com.github.lmh01.lmh01_lib.helpers.WarningHelper;

public class LoadingSummary {
    public static int ModsCount = 1;
    public static int ModAddonCount = 0;
    /*
     * Uses the information got from UpdateChecker to determine if a mod is installed.
     * Also shows the currently installed mod version and if a update is available + the newest version in that case
     * */

    public static void showLoadingSummary() {
        showStartPart();
        showWaringsAndErrorsPart();
        showComponentsPart();

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
            if (ModsCount == 1 && ModAddonCount == 1) {
                send("LMH01_lib has been succesfully Installed with " + ModsCount + " Mod and " + ModAddonCount + " Addon!");
            } else if (ModsCount > 1 && ModAddonCount == 1) {
                send("LMH01_lib has been succesfully Installed with " + ModsCount + " Mods and " + ModAddonCount + " Addon!");
            } else if (ModsCount > 1 && ModAddonCount > 1) {
                send("LMH01_lib has been succesfully Installed with " + ModsCount + " Mods and " + ModAddonCount + " Addons!");
            } else if (ModsCount == 1 && ModAddonCount > 1) {
                send("LMH01_lib has been succesfully Installed with " + ModsCount + " Mod and " + ModAddonCount + " Addons!");
            } else if (ModsCount == 0 && ModAddonCount == 0) {
                send("LMH01_lib has been succesfully Installed with " + ModsCount + " Mods and " + ModAddonCount + " Addons!");
            } else if (ModsCount == 1 && ModAddonCount == 0) {
                send("LMH01_lib has been succesfully Installed with " + ModsCount + " Mod and " + ModAddonCount + " Addons!");
            } else if (ModsCount == 0 && ModAddonCount == 1) {
                send("LMH01_lib has been succesfully Installed with " + ModsCount + " Mods and " + ModAddonCount + " Addon!");
            } else {
                send("LMH01_lib Mod has been succesfully Installed with " + ModsCount + " Mod and " + ModAddonCount + " Addon!");
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

    }
    /**
     * Sends a information into the console with less parameters than {@link DebugHelper#sendDebugInformation(String, int, int, String)}
     * @param message The message sent
     */
    private static void send (String message){
        DebugHelper.sendDebugInformation(message, 1, 0, References.MODID);
    }
}

