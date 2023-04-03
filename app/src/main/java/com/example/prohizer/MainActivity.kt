package com.example.prohizer

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.prohizer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val PERMISSIONS_REQUEST_READ_PHONE_STATE = 1
    private val PERMISSIONS_BIND_STATE = 2
    var binding : ActivityMainBinding? = null
    val REQUEST_CODE_SCREENING_PERMISSION = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val intentFilter = IntentFilter()
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        val myCallReceiver = CallReceiver()
        registerReceiver(myCallReceiver, intentFilter)
        Log.d("Service", "Service is working")
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), PERMISSIONS_REQUEST_READ_PHONE_STATE)
        }


// Проверяем, есть ли у приложения разрешение на использование Call Screening Service
        val hasScreeningPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.BIND_SCREENING_SERVICE
        ) == PackageManager.PERMISSION_GRANTED

// Если нет, то запрашиваем у пользователя разрешение
        if (!hasScreeningPermission) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.BIND_SCREENING_SERVICE),
                REQUEST_CODE_SCREENING_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_SCREENING_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Разрешение получено, можно использовать Call Screening Service
                } else {
                    // Разрешение не получено, нельзя использовать Call Screening Service
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