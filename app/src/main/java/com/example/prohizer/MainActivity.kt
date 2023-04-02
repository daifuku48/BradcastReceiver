package com.example.prohizer

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.prohizer.databinding.ActivityMainBinding
import android.Manifest

class MainActivity : AppCompatActivity() {
    private val PERMISSIONS_REQUEST_READ_PHONE_STATE = 1

    var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val intent = Intent(this, CallTrackingService::class.java)
        Log.d("Service", "Service is working")
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), PERMISSIONS_REQUEST_READ_PHONE_STATE)
        }
        startService(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_READ_PHONE_STATE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    onDestroy()
                }
                return
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}