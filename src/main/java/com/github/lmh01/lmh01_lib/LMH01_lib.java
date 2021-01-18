package com.github.lmh01.lmh01_lib;

import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.util.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(References.MODID)
public class LMH01_lib {
    private static boolean runningOnClientSide = false;

    public LMH01_lib() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
    }

    /*Used to be Pre Init*/
    private void setup(final FMLCommonSetupEvent event) {
        DebugHelper.sendDebugInformation("Beginning Pre-Init", 4);
        //TODO Write config file
        Debug.registerSomeTestModsAndAddons();
        DebugHelper.sendDebugInformation("Working Directory = " + System.getProperty("user.dir"), 4);
    }

    /*Thinks in here only run on client side*/
    private void clientRegistries(final FMLClientSetupEvent event) {
        DebugHelper.sendDebugInformation(References.NAME + " " + References.VERSION + " is running on a client.", 4);
        runningOnClientSide = true;
    }

    /*Used to be Post-Init*/
    private void processIMC(final InterModProcessEvent event) {
        DebugHelper.sendDebugInformation("Starting postInit",4);
        //TODO decide if i should keep the news loader and if yes implement it
        ChildModManager.setAllModsRegistered();
        UpdateCheckerManager.checkForUpdates();
        Thread thread = new Thread(UpdateCheckerManager.RUNNABLE_WAIT_FOR_UPDATES_FINISHED_AND_SEND_LOADING_SUMMARY);
        thread.start();
        DebugHelper.sendDebugInformation("LMH01_lib loading complete:",4);
    }

    /**
     * Returns true if the mod is running on the client side.
     * @return
     */
    public static boolean isRunningOnClientSide(){
        return runningOnClientSide;
    }
}
