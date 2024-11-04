package com.example.carspotteropsc7312poe

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.carspotteropsc7312poe.authentication.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Request notification permission at runtime for Android 13 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestNotificationPermission()
            }
        }

        // Find the login button in the layout by its ID
        val btnLogin = findViewById<Button>(R.id.loginButton)

        // Set an OnClickListener to handle the button click
        btnLogin.setOnClickListener {
            // Create an Intent to navigate to the LoginActivity
            val intent = Intent(this, LoginActivity::class.java)

            // Start the LoginActivity when the button is clicked
            try {
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()

                // Handle the exception by displaying a toast message with an error message
                val errorMessage = "An error occurred: ${e.message}"
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Method to request the notification permission
    private fun requestNotificationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)) {
            // Optionally, provide an additional rationale to the user
            Toast.makeText(
                this,
                "The app needs notification permissions to provide updates.",
                Toast.LENGTH_LONG
            ).show()
        }
        // Request the permission
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            REQUEST_NOTIFICATION_PERMISSION
        )
    }

    companion object {
        private const val REQUEST_NOTIFICATION_PERMISSION = 1001
    }
}
