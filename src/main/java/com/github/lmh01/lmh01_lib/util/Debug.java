package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.helpers.ErrorHelper;
import com.github.lmh01.lmh01_lib.helpers.WarningHelper;

public class Debug {
    /*
    * This class contains some functions that are only used for development purposes.
    * */
    public static void testWarningHelper(){
        WarningHelper.addWarning("Test Warnung 01", References.MODID);
        WarningHelper.addWarning("Test Warnung 02", References.MODID);
        WarningHelper.addWarning("Test Warnung 03", References.MODID);
        WarningHelper.addWarning("Test Warnung 04", References.MODID);
        WarningHelper.addWarning("Test Warnung 05", References.MODID);
        WarningHelper.addWarning("Test Warnung 06", References.MODID);
        DebugHelper.sendDebugInformation(WarningHelper.getListOfWarningsAsString(), 1, 0);
    }
    public static void testErrorHelper(){
        ErrorHelper.addError("Test Error 01", References.MODID);
        ErrorHelper.addError("Test Error 02", References.MODID);
        ErrorHelper.addError("Test Error 03", References.MODID);
        ErrorHelper.addError("Test Error 04", References.MODID);
        ErrorHelper.addError("Test Error 05", References.MODID);
        ErrorHelper.addError("Test Error 06", References.MODID);
        DebugHelper.sendDebugInformation(ErrorHelper.getListOfErrorsAsString(), 1, 0);
    }
    public static void testDebugHelper(){
        DebugHelper.sendDebugInformation("Info", 1, 0);
        DebugHelper.sendDebugInformation("Debug", 2, 0);
        DebugHelper.sendDebugInformation("Warning", 3, 0);
        DebugHelper.sendDebugInformation("Error", 4, 0);
        DebugHelper.sendDebugInformation("Fatal", 5, 0);
        DebugHelper.sendDebugInformation("Debug information send: " + DebugHelper.getAmountOfDebugInformationSent(), 2, 0);
        DebugHelper.sendDebugInformation("Ein anderer Mod", 1, 0, "test_mod");

    }
}
