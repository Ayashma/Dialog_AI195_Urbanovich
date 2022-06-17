package com.example.dialog.second.NorifyRecievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.dialog.R

class AirplaneModeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { actionIntent ->
                if (actionIntent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                    if (getAirplaneMode(context)) {
                        notify(context, "Airplane mode is on")
                    } else {
                        notify(context, "Airplane mode is off")
                    }
                }
            }
        }
    }

    private fun getAirplaneMode(context: Context?) : Boolean {
        return Settings.Global.getInt(context?.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    private fun notify(context: Context, message: String) {
        val builder = NotificationCompat.Builder(context, "1")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Incoming broadcast!")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(context).apply {
            this.notify(0,  builder.build())
        }
    }
}