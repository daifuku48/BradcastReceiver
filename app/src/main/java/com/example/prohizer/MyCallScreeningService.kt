package com.example.prohizer

import android.content.Intent
import android.telecom.Call
import android.telecom.CallScreeningService
import android.util.Log

class MyCallScreeningService : CallScreeningService() {

    override fun onScreenCall(callDetails: Call.Details) {
        val incomingNumber = callDetails.handle.schemeSpecificPart
        Log.d("num", incomingNumber)

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            putExtra("numberPhone", incomingNumber)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        applicationContext.startActivity(intent)
    }
}