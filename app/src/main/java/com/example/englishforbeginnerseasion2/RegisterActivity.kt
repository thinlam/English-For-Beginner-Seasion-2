package com.example.englishforbeginnerseasion2.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.englishforbeginnerseasion2.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_register)

        val fullNameEditText = findViewById<EditText>(R.id.fullNameRegister)
        val emailEditText = findViewById<EditText>(R.id.emailRegister)
        val passwordEditText = findViewById<EditText>(R.id.passwordRegister)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordRegister)
        val registerButton = findViewById<Button>(R.id.btnRegister)
        val toLoginText = findViewById<TextView>(R.id.toLogin)

        registerButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            // Kiểm tra các trường
            if (fullName.isEmpty()) {
                fullNameEditText.error = "Vui lòng nhập họ và tên"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                emailEditText.error = "Vui lòng nhập email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordEditText.error = "Vui lòng nhập mật khẩu"
                return@setOnClickListener
            }

            if (password.length < 6) {
                passwordEditText.error = "Mật khẩu phải có ít nhất 6 ký tự"
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()) {
                confirmPasswordEditText.error = "Vui lòng xác nhận mật khẩu"
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                confirmPasswordEditText.error = "Mật khẩu xác nhận không khớp"
                return@setOnClickListener
            }

            // ✅ Lưu dữ liệu đăng ký vào SharedPreferences
            val prefs = getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
            prefs.edit()
                .putString("fullName", fullName)
                .putString("email", email)
                .putString("password", password)
                .putBoolean("isLoggedIn", false)
                .apply()

            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        toLoginText.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}
