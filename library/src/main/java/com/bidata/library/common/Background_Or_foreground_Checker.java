package com.bidata.library.common;

import android.app.ActivityManager;
import android.content.Context;
import android.os.AsyncTask;

import com.bidata.library.Listener.Background_Or_Foreground_Listener;

import java.util.List;

/**
 * Created by Dell-OP9010 on 1/11/2018.
 */

public class Background_Or_foreground_Checker extends AsyncTask<String, Void, Boolean> {
    Context context;
    Background_Or_Foreground_Listener listener;


    public Background_Or_foreground_Checker(Context context, Background_Or_Foreground_Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        listener.OnComplete(result);
    }
}