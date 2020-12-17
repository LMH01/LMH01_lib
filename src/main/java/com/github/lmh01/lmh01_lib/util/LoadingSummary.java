package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.helpers.ErrorHelper;
import com.github.lmh01.lmh01_lib.helpers.UpdateCheckerHelper;
import com.github.lmh01.lmh01_lib.helpers.WarningHelper;

import java.util.Collections;

public class LoadingSummary {
   /*
     * Uses the information got from UpdateChecker to determine if a mod is installed.
     * Also shows the currently installed mod version and if a update is available + the newest version in that case
     * */

    /**
     * Prints the loading summary in the console. Later it will be supported to print the contents into the
     * chat.
     */
    public static void showLoadingSummary() {
        showStartPart();
        showWaringsAndErrorsPart();
        showLMH01_libVersion();
        showNews();
        if(SubModManager.getModCount() == UpdateCheckerManager.updateAvailable.size()){
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
            if (SubModManager.getModCount() == 1 && SubModManager.getModAddonCount() == 1) {
                send("LMH01_lib has been succesfully Installed with " + SubModManager.getModCount() + " Mod and " + SubModManager.getModAddonCount() + " Addon!");
            } else if (SubModManager.getModCount() > 1 && SubModManager.getModAddonCount() == 1) {
                send("LMH01_lib has been succesfully Installed with " + SubModManager.getModCount() + " Mods and " + SubModManager.getModAddonCount() + " Addon!");
            } else if (SubModManager.getModCount() > 1 && SubModManager.getModAddonCount() > 1) {
                send("LMH01_lib has been succesfully Installed with " + SubModManager.getModCount() + " Mods and " + SubModManager.getModAddonCount() + " Addons!");
            } else if (SubModManager.getModCount() == 1 && SubModManager.getModAddonCount() > 1) {
                send("LMH01_lib has been succesfully Installed with " + SubModManager.getModCount() + " Mod and " + SubModManager.getModAddonCount() + " Addons!");
            } else if (SubModManager.getModCount() == 0 && SubModManager.getModAddonCount() == 0) {
                send("LMH01_lib has been succesfully Installed with " + SubModManager.getModCount() + " Mods and " + SubModManager.getModAddonCount() + " Addons!");
            } else if (SubModManager.getModCount() == 1 && SubModManager.getModAddonCount() == 0) {
                send("LMH01_lib has been succesfully Installed with " + SubModManager.getModCount() + " Mod and " + SubModManager.getModAddonCount() + " Addons!");
            } else if (SubModManager.getModCount() == 0 && SubModManager.getModAddonCount() == 1) {
                send("LMH01_lib has been succesfully Installed with " + SubModManager.getModCount() + " Mods and " + SubModManager.getModAddonCount() + " Addon!");
            } else if (SubModManager.getModCount() > 1 && SubModManager.getModAddonCount() == 0) {
                send("LMH01_lib has been succesfully Installed with " + SubModManager.getModCount() + " Mods and " + SubModManager.getModAddonCount() + " Addons!");
            } else {
                send("LMH01_lib Mod has been succesfully Installed with " + SubModManager.getModCount() + " Mod and " + SubModManager.getModAddonCount() + " Addon!");
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
        /*This part sorts the mods by aplhabet to display them in the correct order in the loading summary. Because
        * the ArrayList registeredModNames is in another order that registeredMods this code sorts them to be
        * displayed correctly*/
        if(SubModManager.getModCount()!=0){
            Collections.sort(SubModManager.registeredModNames);
            Collections.sort(UpdateCheckerManager.newestVersion);
            Collections.sort(SubModManager.downloadURLs);
            for (int i = 0; i < SubModManager.getModCount(); i++){
                for (int n = 0; n < SubModManager.getModCount()*4; n= n+4){
                    if(SubModManager.registeredModNames.get(i).equals(SubModManager.registeredMods.get(n+1))){
                        if(UpdateCheckerManager.updateAvailable.get(i).equals("true")){
                            String tempStorage = SubModManager.registeredModNames.get(i) + " (" + SubModManager.registeredMods.get(n+2) + "): Update available - " + UpdateCheckerManager.newestVersion.get(i) + "; Download: " + SubModManager.downloadURLs.get(i).replace(SubModManager.registeredMods.get(i), "");
                            /*When printing this into the chat the new version number will will be clickable to open the update side*/
                            tempStorage = tempStorage.replace(SubModManager.registeredMods.get(n),"");
                            send(tempStorage);
                        }else{
                            send(SubModManager.registeredModNames.get(i) + " (" + SubModManager.registeredMods.get(n+2) + "): Installed");
                        }
                    }
                }

            }
            if(SubModManager.getModAddonCount() != 0){
                Collections.sort(SubModManager.registeredAddons);
                send("Addons:");
                for (int i = 0; i < SubModManager.getModAddonCount(); i++){
                    send(SubModManager.registeredAddons.get(i));
                }
            }

        }else{
            send("No mods installed");
        }


    }
    private static void showLMH01_libVersion(){
        if(UpdateCheckerManager.newLMH01_libVersionAvailable){
            send("An update for LMH01_lib is available: " + UpdateCheckerManager.newestLMH01_libVersion + " Download - " + References.DOWNLOAD_URL);
        }else{
            send("Version: " + References.VERSION);
        }

    }
    private static void showNews(){
        if(NewsHandler.areNewsAvailable){
            send("News: " + NewsHandler.news);
        }
    }
    /**
     * Sends a information into the console with less parameters than {@link DebugHelper#sendDebugInformation(String, int, int, String)}
     * @param message The message sent
     */
    private static void send (String message){
        DebugHelper.sendDebugInformation(message, 1, 0, References.MODID);
    }
}

