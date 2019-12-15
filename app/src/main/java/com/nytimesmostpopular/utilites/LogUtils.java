package com.nytimesmostpopular.utilites;


import android.util.Log;

import com.nytimesmostpopular.BuildConfig;

/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public class LogUtils {


    public static String debug(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }

        return tag + " , " + message;
    }

    public static String errorLog(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }

        return tag + " , " + message;
    }
}
