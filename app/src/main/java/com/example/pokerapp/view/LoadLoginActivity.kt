package com.example.pokerapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokerapp.viewmodel.LoadLoginViewModel
import com.example.pokerapp.R
import com.example.pokerapp.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_load_login.*

class LoadLoginActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Long = 3000

    private val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val loadLoginViewModel by lazy {
        ViewModelProvider(this).get(LoadLoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_load_login)

        initGif()
        validateName()
        observer()
    }

    private fun initGif(){
        Glide.with(this)
            .load(R.drawable.img_loading)
            .asGif()
            .into(img_loadLogin)
    }

    private fun validateName(){
        loadLoginViewModel.getNameUser()
    }

    private fun observer(){
        loadLoginViewModel.name.observe(this, Observer {
            if (!it.isNullOrEmpty()){
                txt_loadLogin_nome.text = it
                Handler().postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                }, SPLASH_TIME_OUT)

            }
        })
    }


}