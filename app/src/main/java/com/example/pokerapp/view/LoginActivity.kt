package com.example.pokerapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokerapp.viewmodel.LoginViewModel
import com.example.pokerapp.R
import com.example.pokerapp.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityLoginBinding

    private val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )

        initGif()
        setListeners()
        observer()
    }

    private fun initGif(){
        Glide.with(this)
            .load(R.drawable.img_insert_name)
            .asGif()
            .into(img_login)
    }

    override fun onClick(view: View) {
        val id = view.id

        if (id == R.id.button_login){
            handdleLogin()
        }
    }

    private fun setListeners(){
        button_login.setOnClickListener(this)
    }

    private fun handdleLogin(){
        val name = binding.edtLogin.text

        if (name.isNullOrEmpty() || name.length < 4){
            Toast.makeText(this, "O campo de nome não pode ser nulo ou meno que 4 digitos",Toast.LENGTH_LONG).show()
        }else{
            loginViewModel.logar(name.toString())
        }
    }

    private fun observer(){
        loginViewModel.logged.observe(this, Observer {
            if (it){
                startActivity(Intent(this, LoadLoginActivity::class.java))
            }else{
                Toast.makeText(this, "Houve um erro ao logar",Toast.LENGTH_LONG).show()
            }
        })
    }
}