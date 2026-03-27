package com.example.lab1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({

            // 🔥 ГАРАНТИРОВАННАЯ инициализация Firebase
            FirebaseApp.initializeApp(requireContext())

            val nav = NavHostFragment.findNavController(this)

            val auth = FirebaseAuth.getInstance()
            val isLoggedInFirebase = auth.currentUser != null
            val auto = readAutoLogin(requireContext())

            when {
                !isLoggedInFirebase -> nav.navigate(R.id.registerFragment)
                isLoggedInFirebase && !auto -> nav.navigate(R.id.loginFragment)
                else -> nav.navigate(R.id.oneFragment)
            }

        }, 800)

        return root
    }
}