package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.References;
import java.util.ArrayList;

public class WarningHelper {
    private static int Warnings = 0;
    private static final ArrayList<String> LIST_OF_WARNINGS = new ArrayList<>();
    private static final int WARNING_TEXT_BODY_POSITION = DebugHelper.addCommonTextBody("Warning: ");
    //TODO Add a system that sends a chat message to the player when a warning occurs during the game.
    /**
     * ModID will be defaulted as lmh01_lib;
     * This function will register the warning inside an array. The warning will also be shown in the console.
     * @param Description - The description of the warning.
     */
    public static void addWarning(String Description){
        addWarning(Description, References.MODID);
    }

    /**
     * This function will register the warning inside an array. The warning will also be shown in the console.
     * @param Description - The description of the warning.
     * @param ModID - The modid from which the warning has been sent.
     */
    public static void addWarning(String Description, String ModID){
        LIST_OF_WARNINGS.add(ModID + ": " + Description);
        Warnings++;
        DebugHelper.sendDebugInformation(Description, 3, WARNING_TEXT_BODY_POSITION, ModID);
    }

    /**
     * This function will return all registered warnings as a string. One line per warning. Sorted in the way the warnings have been registered.
     */
    public static String getListOfWarningsAsString(){
        String CompletedListOfWarnings = "List of all warnings: ";
        for(int n=0; n<Warnings; n++){
            CompletedListOfWarnings = CompletedListOfWarnings + "\n" + LIST_OF_WARNINGS.get(n);
        }
        DebugHelper.sendDebugInformation("List of all warnings has been created", 4);
        return CompletedListOfWarnings;
    }

    /**
     * This function will return all registered warnings as array.
     */
    public static ArrayList<String> getListOfWarningsAsArray(){
        return LIST_OF_WARNINGS;
    }

    /**
     * This function will return the amount of registered warnings.
     */
    public static int getNumberOfWarnings(){
        return Warnings;
    }
}
