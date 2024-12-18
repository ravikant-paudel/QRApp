package com.khalti.qreye

import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

class QRViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create an instance of CameraView and pass the activity as the LifecycleOwner
        val cameraView = CameraView(this, this as LifecycleOwner)

        // Set the CameraView as the content view of the Activity
        setContentView(cameraView)

        // Alternatively, if you want to add it to an existing layout
        // val layout = findViewById<RelativeLayout>(R.id.container_layout)
        // layout.addView(cameraView)
    }

    // Optionally, you can handle permission requests here if needed
    // Override onRequestPermissionsResult to handle runtime permissions for camera
}