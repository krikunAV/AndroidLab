package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etLogin = findViewById<EditText>(R.id.etEmailOrPhone)
        val etPass = findViewById<EditText>(R.id.etPasswordLogin)
        val cbAuto = findViewById<CheckBox>(R.id.cbAutoLogin)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val login = etLogin.text.toString().trim()
            val pass = etPass.text.toString()

            val savedLogin = readLogin(this)
            val savedPass = readPass(this)

            if (login == savedLogin && pass == savedPass) {
                setAutoLogin(this, cbAuto.isChecked)
                startActivity(Intent(this, ContentActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Неверные данные для входа", Toast.LENGTH_SHORT).show()
            }
        }
    }
}