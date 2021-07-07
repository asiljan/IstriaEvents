package com.siljan.istriaevents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //TODO check auth state and proceed with an action
        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            startActivity(MainActivity.getIntent(this@SplashScreenActivity))
            finish()
        }
    }
}