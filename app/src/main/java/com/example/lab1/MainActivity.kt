package com.example.lab1
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

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
                etLogin.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }

            // чтобы курсор/клавиатура обновились корректно
            etLogin.text?.clear()
        }

        // режим по умолчанию
        setMode(true)

        btnByPhone.setOnClickListener { setMode(true) }
        btnByEmail.setOnClickListener { setMode(false) }

        btnRegister.setOnClickListener {
            val login = etLogin.text.toString().trim()
            val pass = etPass.text.toString()
            val pass2 = etPassRepeat.text.toString()

            // 1) Проверка email на @
            if (!isPhoneMode && !login.contains("@")) {
                Toast.makeText(this, "Email должен содержать символ @", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2) Проверка телефона на +
            if (isPhoneMode && !login.contains("+")) {
                Toast.makeText(this, "Телефон должен содержать символ +", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // (дополнительно, чтобы не было пусто)
            if (login.isEmpty()) {
                Toast.makeText(this, "Поле не должно быть пустым", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3) Пароль минимум 8 символов
            if (pass.length < 8) {
                Toast.makeText(this, "Пароль должен быть минимум 8 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 4) Пароли совпадают
            if (pass != pass2) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Если всё корректно — ничего не делаем (как в задании)
        }
    }
}