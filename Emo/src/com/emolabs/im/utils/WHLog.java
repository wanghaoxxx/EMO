
package com.emolabs.im.utils;

import android.util.Log;

public class WHLog {

    private WHLog() {
    }

    private static final String WH_TAG = "wh";

    public static void i(String msg) {
        Log.i(WH_TAG, msg);
    }

    public static void d(String msg) {
        Log.d(WH_TAG, msg);
    }

    public static void e(String msg, Throwable e) {
        Log.e(WH_TAG, msg, e);
    }

    public static void e(String msg) {
        Log.e(WH_TAG, msg);
    }
}
