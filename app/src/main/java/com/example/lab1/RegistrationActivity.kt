package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    private var isPhoneMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val btnByPhone = findViewById<Button>(R.id.btnByPhone)
        val btnByEmail = findViewById<Button>(R.id.btnByEmail)
        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPass = findViewById<EditText>(R.id.etPasswordReg)
        val etPassRepeat = findViewById<EditText>(R.id.etRepeatPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        fun setMode(phone: Boolean) {
            isPhoneMode = phone
            if (phone) {
                btnByPhone.setBackgroundColor(0xFF6A1B9A.toInt())
                btnByPhone.setTextColor(0xFFFFFFFF.toInt())
                btnByEmail.setBackgroundColor(0xFFDDDDDD.toInt())
                btnByEmail.setTextColor(0xFF222222.toInt())
                etLogin.hint = "Введите телефон (пример: +370...)"
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

        setMode(true)

        btnByPhone.setOnClickListener { setMode(true) }
        btnByEmail.setOnClickListener { setMode(false) }

        btnRegister.setOnClickListener {
            val login = etLogin.text.toString().trim()
            val pass = etPass.text.toString()
            val pass2 = etPassRepeat.text.toString()

            if (login.isEmpty()) {
                Toast.makeText(this, "Поле не должно быть пустым", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isPhoneMode && !login.contains("@")) {
                Toast.makeText(this, "Email должен содержать символ @", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (isPhoneMode && !login.contains("+")) {
                Toast.makeText(this, "Телефон должен содержать символ +", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass.length < 8) {
                Toast.makeText(this, "Пароль должен быть минимум 8 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass != pass2) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ ЛАБА 3: записываем и сразу на ContentActivity
            saveCredentials(this, login, pass)
            setAutoLogin(this, false) // по умолчанию авто-вход не включаем

            startActivity(Intent(this, ContentActivity::class.java))
            finish()
        }
    }
}