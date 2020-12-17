package com.github.lmh01.lmh01_lib;

import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.helpers.ErrorHelper;
import com.github.lmh01.lmh01_lib.helpers.WebHelper;
import com.github.lmh01.lmh01_lib.util.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.net.MalformedURLException;
import java.net.URL;

@Mod(References.MODID)
public class LMH01_lib {

    public LMH01_lib() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
    }

    /*Used to be Pre Init*/
    private void setup(final FMLCommonSetupEvent event) {
        DebugHelper.sendDebugInformation("Beginning Pre-Init", 2, 0);
        /*SubModManager.registerSubMod("trekcraft", "Trek Craft", "1.16.4-1.0.0", "https://www.dropbox.com/s/aepb2kjl5vv9t8m/test_check_01.txt?dl=1", "https://www.curseforge.com/minecraft/mc-mods/trek-craft-1-16");
        SubModManager.registerSubMod("aoe", "All Of Everything", "1.8.9-2.3.0", "https://www.dropbox.com/s/8s0h6tkdg0z8jhl/test_check_02.txt?dl=1", "https://www.curseforge.com/minecraft/mc-mods/all-of-everything-mod");
        SubModManager.registerSubMod("bamb", "Bambus", "1.16.4-1.3.0", "https://www.dropbox.com/s/zrzv4xfspe1g910/test_check_03.txt?dl=1", "https://mcforge.readthedocs.io/en/latest/gettingstarted/structuring/");
        SubModManager.registerSubMod("kek", "KEKW Mod", "1.8.9-4.2.1", "https://www.dropbox.com/s/gbtxep38xdhoqok/test_check_04.txt?dl=1", "https://www.curseforge.com/minecraft/mc-mods/project-298440");
        SubModManager.registerSubMod("auto", "Auto Mod", "1.12.2-1.0.2", "https://www.dropbox.com/s/0qyzixj9glh3849/test_check_05.txt?dl=1", "https://www.google.com/search?client=opera-gx&q=minecraft+forge+modding+1.16+add+command&sourceid=opera&ie=UTF-8&oe=UTF-8");
        SubModManager.registerSubMod("louisc", "Louis Crafting", "1.16.3-0.3.1", "https://www.dropbox.com/s/6tg5b3p5rz4fcw6/test_check_06.txt?dl=1", "https://www.cardcastgame.com");
        SubModManager.registerModAddon("Test Addon");*/
        DebugHelper.sendDebugInformation("Working Directory = " + System.getProperty("user.dir"), 2, 0);
    }

    /*Thinks in here only run on client side*/
    private void clientRegistries(final FMLClientSetupEvent event) {
        DebugHelper.sendDebugInformation("Hello from clientRegistries", 2, 0);
    }

    /*Used to be Post-Init*/
    private void processIMC(final InterModProcessEvent event) {
        DebugHelper.sendDebugInformation("Starting postInit",2, 0);
        UpdateCheckerManager.checkForUpdates();
        DebugHelper.sendDebugInformation("LMH01_lib loading complete:",2, 0);
        //LoadingSummary.showLoadingSummary();
        Thread thread = new Thread(UpdateCheckerManager.runnableWaitForUpdatesFinishedAndSendLoadingSummary);
        thread.start();
    }

}
