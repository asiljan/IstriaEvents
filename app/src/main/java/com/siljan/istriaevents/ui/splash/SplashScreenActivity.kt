package com.siljan.istriaevents.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.siljan.istriaevents.MainActivity
import com.siljan.istriaevents.R
import com.siljan.istriaevents.mvibase.MviView

class SplashScreenActivity : AppCompatActivity(), MviView<SplashViewIntent, SplashViewState> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

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