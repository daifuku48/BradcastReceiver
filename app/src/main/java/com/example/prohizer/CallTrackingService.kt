package com.example.prohizer

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.telephony.TelephonyManager
import android.util.Log


class CallTrackingService : Service() {
    private var callReceiver: CallReceiver? = null
    override fun onCreate() {
        super.onCreate()
        callReceiver = CallReceiver()
        Log.d("Call", "Call receiver is working")
        registerReceiver(callReceiver, IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(callReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}