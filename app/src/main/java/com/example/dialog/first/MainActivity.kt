package com.example.dialog.first

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dialog.R

class MainActivity : AppCompatActivity() {

    private val airplaneModeReceiver = Receiver.AirplaneModeReceiver { message ->
        val dialog = Dialog.newInstance("AirplaneMode", message)
        dialog.show(supportFragmentManager, "tag")
        }

    private val batteryReceiver = Receiver.BatteryReceiver { message ->
        val dialog = Dialog.newInstance("BatteryLow", message)
        dialog.show(supportFragmentManager, "tag")
        }

    private val cameraReceiver = Receiver.CameraReceiver { message ->
        val dialog = Dialog.newInstance("Camera", message)
        dialog.show(supportFragmentManager, "tag")
        }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        registerReceiver(batteryReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            .apply {
            addAction(Intent.ACTION_BATTERY_LOW)
        })

        registerReceiver(cameraReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            .apply {
            addAction(Intent.ACTION_CAMERA_BUTTON)
        })

        registerReceiver(airplaneModeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            .apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        })
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(batteryReceiver)
        unregisterReceiver(cameraReceiver)
        unregisterReceiver(airplaneModeReceiver)
    }
}