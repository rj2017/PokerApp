package com.example.pokerapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokerapp.R
import com.example.pokerapp.viewmodel.SplashViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Long = 3000
    private val splashViewModel by lazy {
     ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        isLogado()
        observer()
    }

    private fun isLogado(){
        splashViewModel.isLogado()
    }

    private fun observer(){
        splashViewModel.logado.observe(this, Observer {
            if (it){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{

                GlobalScope.launch {
                    delay(SPLASH_TIME_OUT)
                    goToNextActivity()

                }
            }
        })
    }

    private fun goToNextActivity(){
        startActivity(Intent(this, LoginActivity::class.java))
    }
}