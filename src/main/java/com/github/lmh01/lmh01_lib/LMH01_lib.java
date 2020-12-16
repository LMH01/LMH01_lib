package com.github.lmh01.lmh01_lib;

import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.helpers.ErrorHelper;
import com.github.lmh01.lmh01_lib.util.LoadingSummary;
import com.github.lmh01.lmh01_lib.util.References;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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

        DebugHelper.sendDebugInformation("Working Directory = " + System.getProperty("user.dir"), 2, 0);
    }

    /*Thinks in here only run on client side*/
    private void clientRegistries(final FMLClientSetupEvent event) {
        DebugHelper.sendDebugInformation("Hello from clientRegistries", 2, 0);
    }

    /*Used to be Post-Init*/
    private void processIMC(final InterModProcessEvent event) {
        DebugHelper.sendDebugInformation("LMH01_lib loading complete:",2, 0);
        LoadingSummary.showLoadingSummary();
    }
}
