package com.github.lmh01.lmh01_lib.helpers;

import com.github.lmh01.lmh01_lib.util.References;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class DebugHelper {
    private static int debugInformationCount;
    private static Logger logger = LogManager.getLogger(References.MODID);
    private static ArrayList<String> commonTextBodies = new ArrayList<String>();
    private static int commonTextBodyNumber = 0;

    /**
     * Sends a debug information into the console.
     * This should be the default function to call.
     * Use {@link DebugHelper#sendDebugInformation(String, int, int, String)} if you want to use a predefined string that comments the information. See {@link DebugHelper#addCommonTextBody(String)} for additional information.
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
        send(debugInformation, level, ModID, "");
    }
    /**
     * Sends a debug information into the console.
     * Additionally you have the option to define a String-Number which comments the debugInformation. See {@link DebugHelper#addCommonTextBody(String)} for additional information.
     * @param debugInformation The information you want to send.
     * @param level The Severity of the Information 1-5;
     *              1 = Fatal
     *              2 = Error
     *              3 = Warn
     *              4 = Info
     *              5 = Debug
     * @param ModID The ModID from which the Information is being sent.
     * @param caseNumber Defines a String that comments the debugInformation. See {@link DebugHelper#addCommonTextBody(String)} for additional information.
     */
    public static void sendDebugInformation(String debugInformation, int level, int caseNumber, String ModID) {
        send(debugInformation, level, ModID, getCommonTextBody(caseNumber));
        debugInformationCount++;
    }
    /**
     * Using this function you don't need provide a ModID. The ModID used will default to lmh01_lib.
     * Additionally you have the option to define a String-Number which comments the debugInformation. See {@link DebugHelper#addCommonTextBody(String)} for additional information.
     * @param debugInformation The information you want to send.
     * @param level The Severity of the Information 1-5;
     *              1 = Fatal
     *              2 = Error
     *              3 = Warn
     *              4 = Info
     *              5 = Debug
     * @param caseNumber Defines a String that comments the debugInformation. See {@link DebugHelper#addCommonTextBody(String)} for additional information.
     */
    public static void sendDebugInformation(String debugInformation, int level, int caseNumber){
        sendDebugInformation(debugInformation, level, caseNumber, References.MODID);
    }
    /**
     * Using this function you dont need to provide a modid. The modid used will default to lmh01_lib.
     * @param debugInformation The information you want to send.
     * @param level The Severity of the Information 1-5;
     *              1 = Fatal
     *              2 = Error
     *              3 = Warn
     *              4 = Info
     *              5 = Debug
     */
    public static void sendDebugInformation(String debugInformation, int level){
        sendDebugInformation(debugInformation, level, 0, References.MODID);
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
    private static void send(String debugInformation, int level, String ModID, String TextBody) {
        if(!ModID.equals("")) {
            logger = LogManager.getLogger(ModID);
        }else {
            logger = LogManager.getLogger(References.MODID);
        }
        if (level == 1) {
            logger.fatal(TextBody + debugInformation);
        }else if(level == 2){
            logger.error(TextBody + debugInformation);
        }else if(level == 3){
            logger.warn(TextBody + debugInformation);
        }else if(level == 4){
            logger.info(TextBody + debugInformation);
        }else if(level == 5){
            logger.debug(TextBody + debugInformation);
        }else{
            logger.debug(debugInformation);
        }
    }
    /**
     * returns the number of debuginformation sent.
     *
     */
    public static int getAmountOfDebugInformationSent(){
        return debugInformationCount;
    }

    /**
     * Adds a common text body to be used later.
     * @param textBody The text body that should be added.
     * @return Returns the number with which the text body can be used later with {@link DebugHelper#getCommonTextBody(int)}.
     */
    public static int addCommonTextBody(String textBody){
        if(commonTextBodies.size() == 0){
            commonTextBodies.add("");
            commonTextBodyNumber++;
        }
        commonTextBodies.add(textBody);
        return commonTextBodyNumber;
    }

    /**
     * Gets the text body from specified position in array.
     * @param textBodyNumber Position in array. {@link DebugHelper#addCommonTextBody(String)} provides the position.
     * @return Returns the text body to be used.
     */
    private static String getCommonTextBody(int textBodyNumber){
        if(textBodyNumber == 0){
            return "";
        }else{
            try {
                return commonTextBodies.get(textBodyNumber);
            }catch(IndexOutOfBoundsException e){
                ErrorHelper.addError("Unable to get textBody: NullPointerException", References.MODID, e);
                return "";
            }
        }

    }
}
