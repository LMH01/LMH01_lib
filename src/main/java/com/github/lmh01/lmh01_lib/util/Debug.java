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
        DebugHelper.sendDebugInformation(WarningHelper.getListOfWarningsAsString(), 5);
    }
    public static void testErrorHelper(){
        ErrorHelper.addError("Test Error 01", References.MODID);
        ErrorHelper.addError("Test Error 02", References.MODID);
        ErrorHelper.addError("Test Error 03", References.MODID);
        ErrorHelper.addError("Test Error 04", References.MODID);
        ErrorHelper.addError("Test Error 05", References.MODID);
        ErrorHelper.addError("Test Error 06", References.MODID);
        DebugHelper.sendDebugInformation(ErrorHelper.getListOfErrorsAsString(), 5);
    }
    public static void testDebugHelper(){
        DebugHelper.sendDebugInformation("Debug", 5);
        DebugHelper.sendDebugInformation("Info", 4);
        DebugHelper.sendDebugInformation("Warning", 3);
        DebugHelper.sendDebugInformation("Error", 2);
        DebugHelper.sendDebugInformation("Fatal", 1);
        DebugHelper.sendDebugInformation("Debug information send: " + DebugHelper.getAmountOfDebugInformationSent(), 2);
        DebugHelper.sendDebugInformation("Ein anderer Mod", 1, "test_mod");

    }
    public static void registerSomeTestModsAndAddons(){
        ChildModManager.registerChildMod("trekcraft", "Trek Craft", "1.16.4-1.0.0", "https://www.dropbox.com/s/aepb2kjl5vv9t8m/test_check_01.txt?dl=1", "https://www.curseforge.com/minecraft/mc-mods/trek-craft-1-16");
        ChildModManager.registerChildMod("aoe", "All Of Everything", "1.8.9-2.3.0", "https://www.dropbox.com/s/8s0h6tkdg0z8jhl/test_check_02.txt?dl=1", "https://www.curseforge.com/minecraft/mc-mods/all-of-everything-mod");
        ChildModManager.registerChildMod("bamb", "Bambus", "1.16.4-1.3.0", "https://www.dropbox.com/s/zrzv4xfspe1g910/test_check_03.txt?dl=1", "https://mcforge.readthedocs.io/en/latest/gettingstarted/structuring/");
        ChildModManager.registerChildMod("kek", "KEKW Mod", "1.8.9-4.2.1", "https://www.dropbox.com/s/gbtxep38xdhoqok/test_check_04.txt?dl=1", "https://www.curseforge.com/minecraft/mc-mods/project-298440");
        ChildModManager.registerChildMod("auto", "Auto Mod", "1.12.2-1.0.2", "https://www.dropbox.com/s/0qyzixj9glh3849/test_check_05.txt?dl=1", "https://www.google.com/search?client=opera-gx&q=minecraft+forge+modding+1.16+add+command&sourceid=opera&ie=UTF-8&oe=UTF-8");
        ChildModManager.registerChildMod("louisc", "Louis Crafting", "1.16.3-0.3.1", "https://www.dropbox.com/s/6tg5b3p5rz4fcw6/test_check_06.txt?dl=1", "https://www.cardcastgame.com");
        ChildModManager.registerModAddon("Test Addon");

    }
    public static void testUpdateChecker(){
        for(int i = 0; i < 6; i++){
            try {
                DebugHelper.sendDebugInformation("Update Available for i = " + i + ": " + UpdateCheckerManager.getUpdateAvailableArrayList().get(i), 2);
                DebugHelper.sendDebugInformation("Newest Version for i = " + i + ": " + UpdateCheckerManager.getNewestVersionArrayList().get(i), 2);
            }catch (NullPointerException e){
                DebugHelper.sendDebugInformation("Nullpointer Excelption: ", 2);
                e.printStackTrace();
            }
        }
    }
}
