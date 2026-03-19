package com.example.blespam

import android.Manifest
import android.bluetooth.*
import android.bluetooth.le.*
import android.os.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private var advertiser: BluetoothLeAdvertiser? = null
    private var advertiseCallback: AdvertiseCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.messageInput)
        val startBtn = findViewById<Button>(R.id.startBtn)
        val stopBtn = findViewById<Button>(R.id.stopBtn)

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        advertiser = bluetoothAdapter.bluetoothLeAdvertiser

        requestPermissions(
            arrayOf(
                Manifest.permission.BLUETOOTH_ADVERTISE,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            1
        )

        startBtn.setOnClickListener {
            val message = input.text.toString().trim()

            if (message.isEmpty()) {
                Toast.makeText(this, "Enter message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            startAdvertising(message)
        }

        stopBtn.setOnClickListener {
            stopAdvertising()
        }
    }

    private fun startAdvertising(message: String) {
        stopAdvertising()

        bluetoothAdapter.name = message.take(20)

        val settings = AdvertiseSettings.Builder()
            .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
            .setConnectable(true)
            .build()

        val data = AdvertiseData.Builder()
            .setIncludeDeviceName(true)
            .build()

        advertiseCallback = object : AdvertiseCallback() {
            override fun onStartSuccess(settingsInEffect: AdvertiseSettings?) {
                Toast.makeText(applicationContext, "Broadcasting", Toast.LENGTH_SHORT).show()
            }

            override fun onStartFailure(errorCode: Int) {
                Toast.makeText(applicationContext, "Failed: $errorCode", Toast.LENGTH_SHORT).show()
            }
        }

        advertiser?.startAdvertising(settings, data, advertiseCallback)
    }

    private fun stopAdvertising() {
        advertiser?.stopAdvertising(advertiseCallback)
    }
}
