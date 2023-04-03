package com.example.prohizer

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log


class CallReceiver : BroadcastReceiver() {
    companion object {
        private var incomingFlag = false;

        private var incoming_number : String? = null
        private var TAG = "PhoneStatReceiver";
    }



    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            incomingFlag = false
            val phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
            Log.i(TAG, "call OUT:$phoneNumber")
        } else {

            //如果是来电
            val tm = context.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
            when (tm.callState) {
                TelephonyManager.CALL_STATE_RINGING -> {
                    incomingFlag = true //标识当前是来电
                    incoming_number = intent.getStringExtra("incoming_number")
                    Log.i(TAG, "RINGING :$incoming_number")
                }
                TelephonyManager.CALL_STATE_OFFHOOK -> if (incomingFlag) {
                    Log.i(TAG, "incoming ACCEPT :$incoming_number")
                }
                TelephonyManager.CALL_STATE_IDLE -> if (incomingFlag) {
                    Log.i(TAG, "incoming IDLE")
                }
            }
        }
    }
}