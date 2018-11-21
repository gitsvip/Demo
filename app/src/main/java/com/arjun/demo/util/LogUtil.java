package com.arjun.demo.util;

import android.util.Log;

public class LogUtil {
    public static final String TAG = "arjun";

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void d(int msg) {
        Log.d(TAG, String.valueOf(msg));
    }
}
