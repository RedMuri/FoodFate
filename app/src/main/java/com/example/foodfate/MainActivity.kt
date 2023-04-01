package com.example.foodfate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), Navigator {

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
    }
    private val navController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNav()
        checkUserSigned()
    }

    private fun checkUserSigned() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val isRegistered = pref.getBoolean(IS_LOGGED, false)
        if (!isRegistered) {
            navController.navigate(R.id.action_mainPageFragment_to_signInFragment)
        }
    }

    private fun setupNav() {
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainPageFragment -> showBottomNav()
                R.id.mapFragment -> showBottomNav()
                R.id.cartFragment -> showBottomNav()
                R.id.favoriteFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.visibility = View.GONE
    }

    override fun navigateFromSignInToMainPage() {
        navController.navigate(R.id.action_signInFragment_to_mainPageFragment)
    }

    override fun navigateFromLogInToMainPage() {
        navController.navigate(R.id.action_logInFragment_to_mainPageFragment)
    }

    override fun navigateFromSignInToLogInPage() {
        navController.navigate(R.id.action_signInFragment_to_logInFragment)
    }

    override fun navigateFromLogInToSignInPage() {
        navController.navigate(R.id.action_logInFragment_to_signInFragment)
    }

    override fun navigateFromCartToSuccessPage() {
        navController.navigate(R.id.action_cartFragment_to_successFragment)
    }

    override fun navigateFromMainPageToMealsPage() {
        navController.navigate(R.id.action_mainPageFragment_to_mealsFragment)
    }

    override fun navigateFromMealsToCartPage() {
        navController.navigate(R.id.action_mealsFragment_to_cartFragment)
    }

    companion object {

        const val IS_LOGGED = "is_logged"
    }
}