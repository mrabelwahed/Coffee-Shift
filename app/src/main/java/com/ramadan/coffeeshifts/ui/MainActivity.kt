package com.ramadan.coffeeshifts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramadan.coffeeshifts.databinding.ActivityMainBinding
import com.ramadan.coffeeshifts.navigation.AppNavigator
import com.ramadan.coffeeshifts.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var appNavigator: AppNavigator
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null)
            appNavigator.navigateTo(Screen.COFFEE_SHIFTS_SCREEN)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0)
            finish()
    }
}