package com.example.englishforbeginnerseasion2

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.englishforbeginnerseasion2.databinding.ActivityMainBinding
import com.example.englishforbeginnerseasion2.ui.AuthActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_vocabulary,
                R.id.nav_quiz,
                R.id.nav_progress,
                R.id.nav_settings // 👈 THÊM dòng này để hỗ trợ menu Cài đặt
            ),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        when (item.itemId) {
            R.id.menu_profile -> {
                val prefs = getSharedPreferences("USER_PREFS", MODE_PRIVATE)
                val name = prefs.getString("fullname", "Không rõ")
                val email = prefs.getString("email", "Chưa có email")
                Toast.makeText(this, "👤 $name\n📧 $email", Toast.LENGTH_LONG).show()
            }

            R.id.menu_logout -> {
                getSharedPreferences("USER_PREFS", MODE_PRIVATE)
                    .edit().putBoolean("isLoggedIn", false).apply()
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }

            R.id.nav_settings -> {
                navController.navigate(R.id.nav_settings) // 👈 Xử lý điều hướng tới fragment_settings
            }

            else -> {
                NavigationUI.onNavDestinationSelected(item, navController)
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
