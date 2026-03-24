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
import com.google.firebase.auth.FirebaseAuth

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

            if (login.isEmpty() || pass.isEmpty()) {
                Toast.makeText(requireContext(), "Заполни email и пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!login.contains("@")) {
                Toast.makeText(requireContext(), "Введи корректный email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val auth = FirebaseAuth.getInstance()
            val nav = NavHostFragment.findNavController(this)

            auth.signInWithEmailAndPassword(login, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        setAutoLogin(requireContext(), cbAuto.isChecked)
                        nav.navigate(R.id.oneFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            task.exception?.localizedMessage ?: "Ошибка входа",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        return root
    }
}