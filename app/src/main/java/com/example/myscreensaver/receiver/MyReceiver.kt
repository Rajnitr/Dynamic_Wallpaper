package com.example.myscreensaver.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.myscreensaver.Worker.MyWorker

class MyReceiver : BroadcastReceiver() {
    val TAG = "raj_dynamic_wallpaper"
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: invoked myreceiver")
        // We are starting MyService via a worker and not directly because since Android 7
        // (but officially since Lollipop!), any process called by a BroadcastReceiver
        // (only manifest-declared receiver) is run at low priority and hence eventually
        // killed by Android. Docs: https://developer.android.com/guide/components/broadcasts#effects-process-state

        val workManager = WorkManager.getInstance(context!!)
        val oneTimeWorkRequest= OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        workManager.enqueue(oneTimeWorkRequest)
    }
}