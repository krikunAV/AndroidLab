package com.example.lab1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            val nav = NavHostFragment.findNavController(this)

            when {
                !hasCredentials(requireContext()) -> nav.navigate(R.id.registerFragment)
                hasCredentials(requireContext()) && !readAutoLogin(requireContext()) -> nav.navigate(R.id.loginFragment)
                else -> nav.navigate(R.id.oneFragment)
            }
        }, 800)

        return root
    }
}