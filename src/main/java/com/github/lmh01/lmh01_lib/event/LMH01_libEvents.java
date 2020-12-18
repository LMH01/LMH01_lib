package com.github.lmh01.lmh01_lib.event;

import com.github.lmh01.lmh01_lib.commands.LMH01Command;
import com.github.lmh01.lmh01_lib.commands.LMH01TestCommand;
import com.github.lmh01.lmh01_lib.helpers.ChatHelper;
import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.util.References;
import com.github.lmh01.lmh01_lib.util.SubModManager;
import com.github.lmh01.lmh01_lib.util.UpdateCheckerManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = References.MODID)
public class LMH01_libEvents {

    private static boolean updateInfoShown = false;
    private static boolean updateInfoShownComplete = false;

    /*Mod commands registry*/
    @SubscribeEvent
    public static void registerCommandsEvent(final RegisterCommandsEvent event){
        DebugHelper.sendDebugInformation("Registering commands..", 1, 0);
        LMH01TestCommand.register(event.getDispatcher());
        LMH01Command.register(event.getDispatcher());
    }
    /*Shows an information in chat when new mod version is available*/
    //TODO Enable the possibility in the config file to get only one notification for each version. (Does not get shown again when starting in the same modversion another time)
    @SubscribeEvent
    public static void showUpdateInfoIngame(final TickEvent.ClientTickEvent event){
        if(Minecraft.getInstance().currentScreen == null && !updateInfoShown){
            updateInfoShown = true;
            for(int i = 0; i<SubModManager.getModCount(); i++){
                if(UpdateCheckerManager.updateAvailable.get(i).contains("true") && !updateInfoShownComplete){
                    SubModManager.printChatNotification();
                    updateInfoShownComplete = true;
                }
            }
            //ChatHelper.sendChatMessage(TextFormatting.BLUE + "Test\n" + TextFormatting.GREEN + "Test 2");
        }
    }
}
