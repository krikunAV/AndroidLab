package com.example.lab1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class ContentActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        bottomNav = findViewById(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)

        // ✅ показываем BottomNav только на One/Two
        navController.addOnDestinationChangedListener { _, dest, _ ->
            bottomNav.visibility = when (dest.id) {
                R.id.oneFragment, R.id.twoFragment -> View.VISIBLE
                else -> View.GONE
            }
        }
    }
}