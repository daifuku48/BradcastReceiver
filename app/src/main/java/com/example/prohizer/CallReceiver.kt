package com.example.prohizer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast





class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE) == TelephonyManager.EXTRA_STATE_OFFHOOK)
        {
            
        }
    }
}