package com.siljan.istriaevents.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.siljan.istriaevents.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity(R.layout.activity_onboarding) {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_onboarding_container_view) as NavHostFragment

        navController = navHostFragment.navController

    }

    companion object {
        fun getIntent(context: Context) = Intent(context, OnboardingActivity::class.java)
    }
}