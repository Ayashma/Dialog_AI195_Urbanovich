package com.example.dialog.first

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings

class Receiver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    class AirplaneModeReceiver(val callback: (String) -> Unit) : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            context.let {
                intent.let { actionIntent ->
                    if (actionIntent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                        if (getAirplaneMode(context)) {
                            callback("Airplane Mode is on")
                        } else {
                            callback("Airplane Mode is off")
                        }
                    }
                }
            }
        }

        private fun getAirplaneMode(context: Context?) : Boolean {
            return Settings.Global.getInt(context
                ?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0
        }
    }

    class BatteryReceiver(val callback: (String) -> Unit) : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            context.let {
                intent.let { actionIntent ->
                    if (actionIntent?.action == Intent.ACTION_BATTERY_LOW) {
                        callback("Low battery percentage")
                    }
                }
            }
        }
    }

    class CameraReceiver(private val callback: (String) -> Unit) : BroadcastReceiver(){

        override fun onReceive(context: Context?, intent: Intent?) {
            context.let {
                intent.let { actionIntent ->
                    if (actionIntent?.action == Intent.ACTION_CAMERA_BUTTON) {
                        callback("Camera is on")
                    }
                }
            }
        }
    }
}