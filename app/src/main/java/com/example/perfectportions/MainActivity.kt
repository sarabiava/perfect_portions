package com.example.perfectportions

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    val homeFragment = HomeFragment()
    val searchFragment = SearchFragment()
    val convertFragment = ConvertFragment()

    private val navigationListener =  NavigationBarView.OnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home -> setCurrentFragment(homeFragment)
            R.id.search -> setCurrentFragment(searchFragment)
            R.id.convert -> setCurrentFragment(convertFragment)
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setCurrentFragment(homeFragment)

        val bottomNav = findViewById<NavigationBarView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener(navigationListener)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

}