package com.example.myscreensaver.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.myscreensaver.MainActivity
import com.example.myscreensaver.R
import com.example.myscreensaver.receiver.MyReceiver
import com.example.myscreensaver.receiver.ScreenLockReceiver

class MyService : Service() {
    val TAG = "raj_dynamic_wallpaper"
    val CHANNEL_ID= "notification_channel"
    private  val screenLockReceiver: ScreenLockReceiver
    var  isServiceRunning : Boolean= false

    companion object {
        var isServiceRunning = false
    }

    init {
          screenLockReceiver= ScreenLockReceiver()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: MyService invoked")
        createNotificationChannel()
        isServiceRunning= true
        // register receiver to listen for screen on events
        val intentFilter= IntentFilter()
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        intentFilter.addAction(Intent.ACTION_USER_PRESENT)
        
        registerReceiver(screenLockReceiver, intentFilter)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //return super.onStartCommand(intent, flags, startId)
        val intent= Intent(this, MainActivity::class.java)
        val pendingIntent= PendingIntent.getService(this, 1000, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification= NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Service is Running")
            .setContentText("Listening for on/off screen events")
            .setContentIntent(pendingIntent)
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setColor(Color.GREEN)
            .build()

        /*
         * A started service can use the startForeground API to put the service in a foreground state,
         * where the system considers it to be something the user is actively aware of and thus not
         * a candidate for killing when low on memory.
         */
        startForeground(1, notification)

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: MyService invoked")
        isServiceRunning= false
        stopForeground(true)

        //unregister receiver
        unregisterReceiver(screenLockReceiver)

        // call MyReceiver which will restart this service via a worker
        val broadcastIntent= Intent(this, MyReceiver::class.java)
        sendBroadcast(broadcastIntent)
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel() {
        val notificationChannel =  NotificationChannel(CHANNEL_ID, getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager= getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)
    }
}