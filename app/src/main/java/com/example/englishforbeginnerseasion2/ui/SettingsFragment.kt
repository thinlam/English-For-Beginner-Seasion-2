package com.example.englishforbeginnerseasion2.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.englishforbeginnerseasion2.R
import com.example.englishforbeginnerseasion2.ui.AuthActivity

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val btnProfile = view.findViewById<Button>(R.id.btn_profile)
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)

        btnProfile.setOnClickListener {
            val prefs = requireContext().getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
            val name = prefs.getString("fullname", "Không rõ")
            val email = prefs.getString("email", "Chưa có email")
            Toast.makeText(requireContext(), "👤 $name\n📧 $email", Toast.LENGTH_LONG).show()
        }

        btnLogout.setOnClickListener {
            requireContext().getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
                .edit().putBoolean("isLoggedIn", false).apply()
            startActivity(Intent(context, AuthActivity::class.java))
            requireActivity().finish()
        }

        return view
    }
}
