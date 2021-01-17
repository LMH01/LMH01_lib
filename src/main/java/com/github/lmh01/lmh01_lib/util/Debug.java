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
        DebugHelper.sendDebugInformation("Debug", 5, 0);
        DebugHelper.sendDebugInformation("Info", 4, 0);
        DebugHelper.sendDebugInformation("Warning", 3, 0);
        DebugHelper.sendDebugInformation("Error", 2, 0);
        DebugHelper.sendDebugInformation("Fatal", 1, 0);
        DebugHelper.sendDebugInformation("Debug information send: " + DebugHelper.getAmountOfDebugInformationSent(), 2, 0);
        DebugHelper.sendDebugInformation("Ein anderer Mod", 1, 0, "test_mod");

    }
    public static void registerSomeTestModsAndAddons(){
        SubModManager.registerSubMod("trekcraft", "Trek Craft", "1.16.4-1.0.0", "https://www.dropbox.com/s/aepb2kjl5vv9t8m/test_check_01.txt?dl=1", "https://www.curseforge.com/minecraft/mc-mods/trek-craft-1-16");
        SubModManager.registerSubMod("aoe", "All Of Everything", "1.8.9-2.3.0", "https://www.dropbox.com/s/8s0h6tkdg0z8jhl/test_check_02.txt?dl=1", "https://www.curseforge.com/minecraft/mc-mods/all-of-everything-mod");
        SubModManager.registerSubMod("bamb", "Bambus", "1.16.4-1.3.0", "https://www.dropbox.com/s/zrzv4xfspe1g910/test_check_03.txt?dl=1", "https://mcforge.readthedocs.io/en/latest/gettingstarted/structuring/");
        SubModManager.registerSubMod("kek", "KEKW Mod", "1.8.9-4.2.1", "https://www.dropbox.com/s/gbtxep38xdhoqok/test_check_04.txt?dl=1", "https://www.curseforge.com/minecraft/mc-mods/project-298440");
        SubModManager.registerSubMod("auto", "Auto Mod", "1.12.2-1.0.2", "https://www.dropbox.com/s/0qyzixj9glh3849/test_check_05.txt?dl=1", "https://www.google.com/search?client=opera-gx&q=minecraft+forge+modding+1.16+add+command&sourceid=opera&ie=UTF-8&oe=UTF-8");
        SubModManager.registerSubMod("louisc", "Louis Crafting", "1.16.3-0.3.1", "https://www.dropbox.com/s/6tg5b3p5rz4fcw6/test_check_06.txt?dl=1", "https://www.cardcastgame.com");
        SubModManager.registerModAddon("Test Addon");

    }
    public static void testUpdateChecker(){
        for(int i = 0; i < 6; i++){
            try {
                DebugHelper.sendDebugInformation("Update Available for i = " + i + ": " + UpdateCheckerManager.getUpdateAvailableArrayList().get(i), 2, 0);
                DebugHelper.sendDebugInformation("Newest Version for i = " + i + ": " + UpdateCheckerManager.getNewestVersionArrayList().get(i), 2, 0);
            }catch (NullPointerException e){
                DebugHelper.sendDebugInformation("Nullpointer Excelption: ", 2, 0);
                e.printStackTrace();
            }
        }
    }
}
