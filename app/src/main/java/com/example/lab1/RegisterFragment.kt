package com.example.lab1

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private var isPhoneMode = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        val btnByPhone = root.findViewById<Button>(R.id.btnByPhone)
        val btnByEmail = root.findViewById<Button>(R.id.btnByEmail)
        val etLogin = root.findViewById<EditText>(R.id.etLogin)
        val etPass = root.findViewById<EditText>(R.id.etPasswordReg)
        val etPassRepeat = root.findViewById<EditText>(R.id.etRepeatPassword)
        val btnRegister = root.findViewById<Button>(R.id.btnRegister)

        fun setMode(phone: Boolean) {
            isPhoneMode = phone

            if (phone) {
                btnByPhone.setBackgroundColor(0xFF6A1B9A.toInt())
                btnByPhone.setTextColor(0xFFFFFFFF.toInt())
                btnByEmail.setBackgroundColor(0xFFDDDDDD.toInt())
                btnByEmail.setTextColor(0xFF222222.toInt())

                etLogin.hint = "Для Firebase нужен email. Выберите «По email»"
                etLogin.inputType = InputType.TYPE_CLASS_PHONE
            } else {
                btnByEmail.setBackgroundColor(0xFF6A1B9A.toInt())
                btnByEmail.setTextColor(0xFFFFFFFF.toInt())
                btnByPhone.setBackgroundColor(0xFFDDDDDD.toInt())
                btnByPhone.setTextColor(0xFF222222.toInt())

                etLogin.hint = "Введите email (пример: name@mail.com)"
                etLogin.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }

            etLogin.text?.clear()
        }

        // можно оставить режим по умолчанию как был, но для Firebase удобнее email:
        setMode(false)

        btnByPhone.setOnClickListener { setMode(true) }
        btnByEmail.setOnClickListener { setMode(false) }

        btnRegister.setOnClickListener {
            val login = etLogin.text.toString().trim()
            val pass = etPass.text.toString()
            val pass2 = etPassRepeat.text.toString()

            // Firebase для этой лабы: только email
            if (isPhoneMode) {
                Toast.makeText(requireContext(), "Для Firebase выбери режим «По email»", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (login.isEmpty()) {
                Toast.makeText(requireContext(), "Email не должен быть пустым", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!login.contains("@")) {
                Toast.makeText(requireContext(), "Email должен содержать символ @", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass.length < 8) {
                Toast.makeText(requireContext(), "Пароль должен быть минимум 8 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass != pass2) {
                Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val auth = FirebaseAuth.getInstance()
            val nav = NavHostFragment.findNavController(this)

            auth.createUserWithEmailAndPassword(login, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // оставляем только настройку авто-входа (как в лабах)
                        setAutoLogin(requireContext(), false)
                        nav.navigate(R.id.oneFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            task.exception?.localizedMessage ?: "Ошибка регистрации",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        return root
    }
}