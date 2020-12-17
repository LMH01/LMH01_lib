package com.github.lmh01.lmh01_lib.util;

import com.github.lmh01.lmh01_lib.helpers.WebHelper;

public class NewsHandler {
    public static String news = "";
    public static boolean areNewsAvailable = false;
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
}
