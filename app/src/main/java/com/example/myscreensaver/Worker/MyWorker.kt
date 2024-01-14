package com.example.myscreensaver.Worker

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myscreensaver.service.MyService

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    val TAG = "raj_dynamic_wallpaper"

    override fun doWork(): Result {

        if(MyService.isServiceRunning==false){
            Log.d(TAG, "starting service from doWork")
            val intent = Intent(this.applicationContext, MyService::class.java)

            /*
             * startForegroundService is similar to startService but with an implicit promise
             * that the service will call startForeground once it begins running.
             * The service is given an amount of time comparable to the ANR interval to do this,
             * otherwise the system will automatically stop the service and declare the app ANR.
             */

            /*
             * startForegroundService is similar to startService but with an implicit promise
             * that the service will call startForeground once it begins running.
             * The service is given an amount of time comparable to the ANR interval to do this,
             * otherwise the system will automatically stop the service and declare the app ANR.
             */
            ContextCompat.startForegroundService(this.applicationContext, intent)
        }
        return Result.success()
    }

    override fun onStopped() {
        Log.d(TAG, "onStopped called for: " + this.id)
        super.onStopped()
    }
}
