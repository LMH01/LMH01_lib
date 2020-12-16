package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.References;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebugHelper {
    public static boolean activateDebugInfo = true;
    private static int debugInformationCount;
    private static int DebugLevel;
    private static Logger logger = LogManager.getLogger(References.MODID);
    private static boolean disabledDebugInfoShown = false;

    /**
     * Using this function you dont need provide a modid. The modid used will default to lmh01_lib.
     * @param DebugInformation - The Information you want to send
     * @param level - The Severity of the Information 1-5;
     *              1 = Info
     *              2 = Debug
     *              3 = Warn
     *              4 = Error
     *              5 = Fatal
     * @param sendFrom - Defines a String that comments the DebugInformation
     */
    public static void sendDebugInformation(String DebugInformation, int level, int sendFrom){
        sendDebugInformation(DebugInformation, level, sendFrom, References.MODID);
    }
    /**
     *
     * @param DebugInformation - The Information you want to send
     * @param level - The Severity of the Information 1-5;
     *              1 = Info
     *              2 = Debug
     *              3 = Warn
     *              4 = Error
     *              5 = Fatal
     * @param MODID - The ModID from which the Information is being sent
     * @param sendFrom - Defines a String that comments the DebugInformation
     */
    public static void sendDebugInformation(String DebugInformation, int level, int sendFrom, String MODID) {
        String TextBody = "";
        if(activateDebugInfo) {
            /*
             * SendFrom:
             * 0 = Send as Standard Info
             * 1 = Send DebugInfo as Error
             * 2 = Send DebugInfo from Config
             * 3 = Send DebugInfo from ItemRegistering
             * 4 = Send DebugInfo from ItemCrafting
             * 5 = Send DebugInfo from BlockRegistering
             * 6 = Send DebugInfo from BlockCrafting
             * 7 = Send DebugInfo from Error Helper
             * 8 = Send DebugInfo from Furnace Recipe
             * 9 = Send DebugInfo from Warning Helper
             * */
            /*Setting the Text Body for the DebugSendingProcess*/
            switch (sendFrom) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    TextBody = "Loading Config for variable name: ";
                    break;
                case 3:
                    TextBody = "Registering Item: ";
                    break;
                case 4:
                    TextBody = "Registering Crafting Recipe for Item: ";
                    break;
                case 5:
                    TextBody = "Registering Block: ";
                    break;
                case 6:
                    TextBody = "Registering Crafting Recipe for Block";
                    break;
                case 7:
                    TextBody = "An ERROR occurred: ";
                    break;
                case 8:
                    TextBody = "Registering Furnace Recipe for Item: ";
                    break;
                case 9:
                    TextBody = "Warning: ";
                    break;
                default:
                    System.err.println("The Level from the 'sendFrom' Variable is not Valid! Reason this info has been send: " + DebugInformation);
                    break;
            }
            send(DebugInformation, level, MODID, TextBody);
            debugInformationCount++;
        }else{
            if(!disabledDebugInfoShown){
                send("Debug information sending has been disabled for all LMH01 mods! This was the last message!",3, "", "");
                disabledDebugInfoShown = true;
            }

        }
    }
    private static void send(String DebugInformation, int level, String MODID, String TextBody) {
        if(!MODID.equals("")) {
            logger = LogManager.getLogger(MODID);
        }else {
            logger = LogManager.getLogger(References.MODID);
        }
        if (level == 1) {
            logger.info(TextBody + DebugInformation);
        }else if(level == 2){
            logger.debug(TextBody + DebugInformation);
        }else if(level == 3){
            logger.warn(TextBody + DebugInformation);
        }else if(level == 4){
            logger.error(TextBody + DebugInformation);
        }else if(level == 5){
            logger.fatal(TextBody + DebugInformation);
        }else{
            logger.debug(DebugInformation);
        }
    }
    /**
     * returns the number of debuginformation sent.
     *
     */
    public static int getAmountOfDebugInformationSent(){
        return debugInformationCount;
    }
}
