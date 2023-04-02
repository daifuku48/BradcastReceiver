package com.example.prohizer

import android.content.Intent
import android.telecom.Call
import android.telecom.CallScreeningService

class MyCallScreeningService : CallScreeningService() {
    override fun onScreenCall(callDetails: Call.Details) {
        val incomingNumber = callDetails.handle.schemeSpecificPart
        val intent = Intent("com.example.prohizer")
        intent.putExtra("incoming_number", incomingNumber)
        sendBroadcast(intent)
        respondToCall(callDetails, CallResponse.Builder().build())
    }
}