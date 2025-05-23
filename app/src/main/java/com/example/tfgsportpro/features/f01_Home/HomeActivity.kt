package com.example.tfgsportpro.features.f01_Home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityHomeBinding
import com.example.tfgsportpro.features.f01_Home.fragments.resumePage.ResumeFragment
import com.example.tfgsportpro.features.f01_Home.fragments.mePage.MeFragment
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.TrainingFragment
import com.example.tfgsportpro.features.f00_Auth.login.useCase.LoginManager
import com.google.firebase.auth.FirebaseAuth
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.tfgsportpro.features.f00_Auth.login.ui.LoginActivity

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private lateinit var loginManager: LoginManager
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        loginManager = LoginManager(this)

        val lastFragmentTag = sharedPref.getString("last_fragment", "TrainingFragment")
        val fragmentToShow = when (lastFragmentTag) {
            "MeFragment" -> MeFragment()
            "ResumeFragment" -> ResumeFragment()
            else -> TrainingFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragmentToShow)
            .commit()

        val selectedItemId = when (lastFragmentTag) {
            "MeFragment" -> R.id.navMe
            "ResumeFragment" -> R.id.navResume
            else -> R.id.navTraining
        }
        binding.bNavigation.selectedItemId = selectedItemId

        val darkMode = sharedPref.getBoolean("dark_mode", false)
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        if (FirebaseAuth.getInstance().currentUser != null) {
            loginManager.updateStreakOnAutoLogin()
        }

        binding.bNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navTraining -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, TrainingFragment()).commit()
                    true
                }
                R.id.navMe -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MeFragment()).commit()
                    true
                }
                R.id.navResume -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ResumeFragment()).commit()
                    true
                }
                else -> false
            }
        }

        binding.bFinish.setOnClickListener {
            finish()
        }

        setSupportActionBar(binding.toolbar)
    }

    //Items del menú 3 puntos de arriba
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow, menu)

        val textColor = ContextCompat.getColor(this, R.color.colorLetra)

        for (i in 0 until (menu?.size() ?: 0)) {
            val menuItem = menu?.getItem(i)
            val spannableTitle = SpannableString(menuItem?.title)
            spannableTitle.setSpan(ForegroundColorSpan(textColor), 0, spannableTitle.length, 0)
            menuItem?.title = spannableTitle
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.b_profile -> {
                navigateToProfile()
            }
            R.id.switchTheme -> {
                toggleDarkMode()
            }
            R.id.b_logOut -> {
                logOut()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun toggleDarkMode() {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        val editor = sharedPref.edit()

        val currentFragmentTag = when (supportFragmentManager.findFragmentById(R.id.fragmentContainerView)) {
            is TrainingFragment -> "TrainingFragment"
            is MeFragment -> "MeFragment"
            is ResumeFragment -> "ResumeFragment"
            else -> "TrainingFragment"
        }
        editor.putString("last_fragment", currentFragmentTag)

        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            editor.putBoolean("dark_mode", false)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            editor.putBoolean("dark_mode", true)
        }

        editor.apply()
    }


    private fun navigateToProfile() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MeFragment()).addToBackStack(null).commit()
        binding.bNavigation.selectedItemId = R.id.navMe
    }

    private fun logOut() {
        FirebaseAuth.getInstance().signOut()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onDestroy() {
        super.onDestroy()
        binding.bNavigation.setOnItemSelectedListener(null)
    }
}
