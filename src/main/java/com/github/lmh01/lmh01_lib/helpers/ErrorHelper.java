package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.References;
import java.util.ArrayList;

public class ErrorHelper {
    private static int Errors = 0;
    private static final ArrayList<String> LIST_OF_ERRORS = new ArrayList<>();
    public static final int ERROR_TEXT_BODY_POSITION = DebugHelper.addCommonTextBody("An Error occurred: ");
    /**
     * ModID will be defaulted as lmh01_lib
     * This function will register the error inside an array. The error will also be shown in the console.
     * @param Description - The description of the error.
     */
    public static void addError(String Description){
        addError(Description, References.MODID);
    }
    /**
     * This function will register the error inside an array. The error will also be shown in the console.
     * @param description The description of the error.
     * @param ModID The modid from which the error has been sent.
     */
    public static void addError(String description, String ModID){
        LIST_OF_ERRORS.add(ModID + ": " + description);
        Errors++;
        DebugHelper.sendDebugInformation(description, 4, ERROR_TEXT_BODY_POSITION);
    }

    /**
     * This function will register the error inside an array. The error will also be shown in the console.
     * Additionally the stacktrace is being printed.
     * @param description The description of the error.
     * @param modid The modid from which the error has been sent.
     * @param exception The exception.
     */
    public static void addError(String description, String modid, Exception exception){
        LIST_OF_ERRORS.add(modid + ": " + description + "");
        Errors++;
        DebugHelper.sendDebugInformation(description, 4, ERROR_TEXT_BODY_POSITION);
        DebugHelper.sendException(exception, modid);
    }
    /**
     * This function will return all registered errors as a string. Per line one warning. Sorted in the way the errors have been registered.
     */
    public static String getListOfErrorsAsString(){
        String CompletedListOfWarnings = "List of all errors: ";
        for(int n=0; n<Errors; n++){
            CompletedListOfWarnings = CompletedListOfWarnings + "\n" + LIST_OF_ERRORS.get(n);
        }
        DebugHelper.sendDebugInformation("List of all errors has been created", 4);
        return CompletedListOfWarnings;
    }
    /**
     * This function will return all registered errors as array.
     */
    public static ArrayList<String> getListOfErrorsAsArray(){
        return LIST_OF_ERRORS;
    }
    /**
     * This function will return the amount of registered warnings.
     */
    public static int getNumberOfErrors(){
        return Errors;
    }
}
