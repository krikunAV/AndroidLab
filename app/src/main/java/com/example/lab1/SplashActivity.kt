package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // маленькая задержка, чтобы был виден ProgressBar
        Handler(Looper.getMainLooper()).postDelayed({

            val target = when {
                !hasCredentials(this) -> RegistrationActivity::class.java
                hasCredentials(this) && !readAutoLogin(this) -> LoginActivity::class.java
                else -> ContentActivity::class.java
            }

            startActivity(Intent(this, target))
            finish()
        }, 800)
    }
}