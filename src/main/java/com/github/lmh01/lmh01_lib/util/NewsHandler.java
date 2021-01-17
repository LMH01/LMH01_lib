package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.WebHelper;

public class NewsHandler {
    private static String news = "";
    private static boolean areNewsAvailable = false;

    /**
     * Loads the news for lmh01_lib.
     */
    public static void loadNews(){
        new Thread("LMH01_lib news Loader"){
            public void run(){
                news = WebHelper.getContentFromURL(References.NEWS_URL);
                if(!news.isEmpty()){
                    areNewsAvailable = true;
                }
            }
        }.start();
    }

    /**
     * Returns true if news are available
     * @return
     */
    public static boolean areNewsAvailable(){
        return areNewsAvailable;
    }

    /**
     * Returns the news that have been loaded.
     * @return
     */
    public static String getNews(){
        return news;
    }
}
