package com.siljan.istriaevents.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.siljan.istriaevents.MainActivity
import com.siljan.istriaevents.R
import com.siljan.istriaevents.common.BaseView
import com.siljan.istriaevents.ui.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity(), BaseView<SplashIntent, SplashUIState> {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        viewModel.states().observe(this) {
            render(it)
        }

        viewModel.processIntent(SplashIntent.UserAuthenticationCheck)
    }

    private fun launchMainMode() {
        startActivity(MainActivity.getIntent(this))
        finish()
    }

    private fun launchLoginRegisterFlow() {
        startActivity(OnboardingActivity.getIntent(this))
    }

    override fun render(state: SplashUIState) {
        when (state) {
            is SplashUIState.UserExists -> launchMainMode()
            SplashUIState.GuestUser -> launchLoginRegisterFlow()
        }
    }
}