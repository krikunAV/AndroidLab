package com.example.lab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        val etLogin = root.findViewById<EditText>(R.id.etEmailOrPhone)
        val etPass = root.findViewById<EditText>(R.id.etPasswordLogin)
        val cbAuto = root.findViewById<CheckBox>(R.id.cbAutoLogin)
        val btnLogin = root.findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val login = etLogin.text.toString().trim()
            val pass = etPass.text.toString()

            val savedLogin = readLogin(requireContext())
            val savedPass = readPass(requireContext())

            if (login == savedLogin && pass == savedPass) {
                setAutoLogin(requireContext(), cbAuto.isChecked)
                NavHostFragment.findNavController(this).navigate(R.id.oneFragment)
            } else {
                Toast.makeText(requireContext(), "Неверные данные для входа", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }
}