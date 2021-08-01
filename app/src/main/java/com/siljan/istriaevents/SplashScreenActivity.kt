package com.siljan.istriaevents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.siljan.istriaevents.mvibase.MviView
import com.siljan.istriaevents.ui.splash.SplashViewIntent
import com.siljan.istriaevents.ui.splash.SplashViewState
import com.siljan.istriaevents.viewmodel.SplashViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity(), MviView<SplashViewIntent, SplashViewState> {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        viewModel.states().observe(this, { render(it) })

        viewModel.processIntent(SplashViewIntent.CheckAuthState)

    }

    override fun render(state: SplashViewState) {
        if (state.session) {
            launchMainMode()
        }
    }

    private fun launchMainMode() {
        startActivity(MainActivity.getIntent(this))
        finish()
    }
}