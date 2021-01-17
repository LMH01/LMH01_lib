package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.References;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class DebugHelper {
    private static int debugInformationCount;
    private static Logger logger = LogManager.getLogger(References.MODID);
    private static final ArrayList<String> COMMON_TEXT_BODIES = new ArrayList<>();

    /**
     * Sends a debug information into the console.
     * This should be the default function to call.
     * @param debugInformation The information you want to send.
     * @param level The Severity of the Information 1-5;
     *              1 = Fatal
     *              2 = Error
     *              3 = Warn
     *              4 = Info
     *              5 = Debug
     * @param ModID The ModID from which the Information es being sent.
     */
    public static void sendDebugInformation(String debugInformation, int level, String ModID){
        send(debugInformation, level, ModID);
    }
    /**
     * Using this function you don't need to provide a modid. The modid used will default to lmh01_lib.
     * @param debugInformation The information you want to send.
     * @param level The Severity of the Information 1-5;
     *              1 = Fatal
     *              2 = Error
     *              3 = Warn
     *              4 = Info
     *              5 = Debug
     */
    public static void sendDebugInformation(String debugInformation, int level){
        send(debugInformation, level, References.MODID);
    }

    /**
     * Sends an exception into the console.
     * @param exception The exception.
     * @param modid The modid from which the exception is being sent.
     */
    public static void sendException(Exception exception, String modid){
        logger = LogManager.getLogger(modid);
        logger.error(exception);
    }
    private static void send(String debugInformation, int level, String ModID) {
        if(!ModID.equals("")) {
            logger = LogManager.getLogger(ModID);
        }else {
            logger = LogManager.getLogger(References.MODID);
        }
        if (level == 1) {
            logger.fatal(debugInformation);
        }else if(level == 2){
            logger.error(debugInformation);
        }else if(level == 3){
            logger.warn(debugInformation);
        }else if(level == 4){
            logger.info(debugInformation);
        }else if(level == 5){
            logger.debug(debugInformation);
        }else{
            logger.debug(debugInformation);
        }
        debugInformationCount++;
    }
    /**
     * returns the number of debug information sent.
     *
     */
    public static int getAmountOfDebugInformationSent(){
        return debugInformationCount;
    }
}
