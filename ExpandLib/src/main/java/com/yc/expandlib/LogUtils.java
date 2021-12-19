package com.yc.expandlib;

import android.util.Log;

public final class LogUtils {

    private static boolean isLog = false;

    public static boolean isIsLog() {
        return isLog;
    }

    public static void setIsLog(boolean isLog) {
        LogUtils.isLog = isLog;
    }

    public static void d(String log){
        if (isLog){
            Log.d("LogUtils",log);
        }
    }

}
