package com.android_lesson

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityZ2Binding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

class Z2Activity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityZ2Binding
    private var locationPermission = false
    private lateinit var flpc: FusedLocationProviderClient
    private lateinit var locationTask: Task<Location>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityZ2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        flpc = LocationServices.getFusedLocationProviderClient(this)

        viewBinding.buttonGetLocation.setOnClickListener {
            locationPermission = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            if (!locationPermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    100
                )
            } else {

            }
        }
    }

    fun getLocation() {
        locationTask.addOnSuccessListener { it ->
            if (it != null) {
                viewBinding.textViewLocationLatitude.text =
                    getString(R.string.latitude_format, it.latitude)
                viewBinding.textViewLocationLongitude.text =
                    getString(R.string.longitude_format, it.longitude)
            } else {
                viewBinding.textViewLocationLatitude.text =
                    getString(R.string.location_unavailable)
                viewBinding.textViewLocationLongitude.text =
                    getString(R.string.location_unavailable)
            }
        }
        locationTask.addOnFailureListener {

        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        locationPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermission = true
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                locationTask = flpc.lastLocation
                getLocation()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}