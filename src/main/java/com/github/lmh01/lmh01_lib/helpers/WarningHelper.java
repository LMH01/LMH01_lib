package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.References;
import java.util.ArrayList;

public class WarningHelper {
    private static int Warnings = 0;
    private static ArrayList<String> ListOfWarnings = new ArrayList<String>();
    public static int warningTextBodyPosition = DebugHelper.addCommonTextBody("Warning: ");
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
        ListOfWarnings.add(ModID + ": " + Description);
        Warnings++;
        DebugHelper.sendDebugInformation(Description, 3, warningTextBodyPosition, ModID);
    }
    /**
     * This function will return all registered warnings as a string. One line per warning. Sorted in the way the warnings have been registered.
     */
    public static String getListOfWarningsAsString(){
        String CompletedListOfWarnings = "List of all warnings: ";
        for(int n=0; n<Warnings; n++){
            CompletedListOfWarnings = CompletedListOfWarnings + "\n" + ListOfWarnings.get(n);
        }
        DebugHelper.sendDebugInformation("List of all warnings has been created", 4);
        return CompletedListOfWarnings;
    }
    /**
     * This function will return all registered warnings as array.
     */
    public static ArrayList<String> getListOfWarningsAsArray(){
        return ListOfWarnings;
    }
    /**
     * This function will return the amount of registered warnings.
     */
    public static int getNumberOfWarnings(){
        return Warnings;
    }
}
