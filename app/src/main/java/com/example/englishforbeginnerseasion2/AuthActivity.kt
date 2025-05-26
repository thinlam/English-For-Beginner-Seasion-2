package com.example.englishforbeginnerseasion2.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.englishforbeginnerseasion2.MainActivity
import com.example.englishforbeginnerseasion2.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Tự động chuyển nếu đã đăng nhập trước đó
        val prefs = getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }


        setContentView(R.layout.fragment_login) // ✅ Đảm bảo đây là layout đăng nhập (KHÔNG phải fragment_login)

        val emailEditText = findViewById<EditText>(R.id.emailLogin)
        val passwordEditText = findViewById<EditText>(R.id.passwordLogin)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerText = findViewById<TextView>(R.id.toRegister)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty()) {
                emailEditText.error = "Vui lòng nhập Email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordEditText.error = "Vui lòng nhập Mật khẩu"
                return@setOnClickListener
            }

            if (password.length < 6) {
                passwordEditText.error = "Mật khẩu phải từ 6 ký tự trở lên"
                return@setOnClickListener
            }

            val registeredEmail = prefs.getString("email", null)
            val registeredPassword = prefs.getString("password", null)

            if (email == registeredEmail && password == registeredPassword) {
                prefs.edit().putBoolean("isLoggedIn", true).apply() // ✅ Lưu trạng thái đã đăng nhập
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show()
            }
        }

        registerText.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}
