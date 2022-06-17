package com.example.dialog.second

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import com.example.dialog.second.NorifyRecievers.AirplaneModeReceiver
import com.example.dialog.second.NorifyRecievers.BatteryReceiver
import com.example.dialog.second.NorifyRecievers.CameraReceiver


class App : Application() {

    private val batteryReceiver = BatteryReceiver()
    private val cameraReceiver = CameraReceiver()
    private val airplaneModeReceiver = AirplaneModeReceiver()

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descText = "Important notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel("1", descText, importance)
            mChannel.description = descText

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val batteryFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_BATTERY_LOW)
        }
        registerReceiver(batteryReceiver, batteryFilter)

        val cameraFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_CAMERA_BUTTON)
        }
        registerReceiver(cameraReceiver, cameraFilter)

        val flyModeFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(airplaneModeReceiver, flyModeFilter)
    }

    override fun onTerminate() {
        super.onTerminate()

        unregisterReceiver(batteryReceiver)
        unregisterReceiver(cameraReceiver)
        unregisterReceiver(airplaneModeReceiver)
    }
}