package com.example.myscreensaver.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.myscreensaver.MyScreenSaverUtils

class ScreenLockReceiver : BroadcastReceiver() {
    val TAG = "raj_dynamic_wallpaper"

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if(action== Intent.ACTION_SCREEN_ON) {
            Log.d(TAG, "onReceive: ACTION_SCREEN_ON")
        }
        else if (action== Intent.ACTION_SCREEN_OFF) {
            Log.d(TAG, "onReceive: ACTION_SCREEN_OFF")
        }
        else if(action==Intent.ACTION_USER_PRESENT) {
            Log.d(TAG, "onReceive: ACTION_USER_PRESENT")
            if (context != null) {
                MyScreenSaverUtils().setWallpaper(context)
            }
        }
    }
}