package com.siljan.istriaevents.ui.onboarding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.siljan.istriaevents.R

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, OnboardingActivity::class.java)
    }
}