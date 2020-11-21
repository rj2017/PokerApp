package com.example.pokerapp.view

import android.app.ActionBar
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokerapp.R
import com.example.pokerapp.databinding.ActivityMainBinding
import com.example.pokerapp.view.adapters.MainActivityAdapter
import com.example.pokerapp.viewmodel.MainViewModel
import com.example.pokerapp.viewmodel.factory.MainViewModelFactory
import kotlinx.android.synthetic.main.item_toolbar.*

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnKeyListener {

    private lateinit var binding : ActivityMainBinding

    private val mainViewModel by lazy {
        val factory = MainViewModelFactory(application)
        ViewModelProvider(this,factory).get(MainViewModel::class.java)
    }

    private val viewAdapter : MainActivityAdapter by lazy {
        MainActivityAdapter(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        findViewById<RecyclerView>(binding.recyclerMain.id).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = viewAdapter

        }

        edt_item_toolbar.setOnKeyListener(this)
        img_about_toolbar.setOnClickListener(this)
        img_exit.setOnClickListener(this)

        //mainViewModel.islogged()
        observe()
        setupActionBar()
        setList()
    }


    private fun setList(){
        mainViewModel.getAllPokemons()
    }

    private fun setupActionBar(){
        val actionBar : ActionBar? = actionBar
        actionBar?.let {
            it.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            it.setCustomView(R.layout.item_toolbar)
            it.show()
        }

    }

    private fun observe(){
        mainViewModel.listDetailPokemon.observe(this, Observer {
            if (it.isSucess()){
                viewAdapter.updateList(it.data!!)
                val s = ""
            }else{
                Toast.makeText(this,it.error?.message,Toast.LENGTH_SHORT).show()
            }
        })
        mainViewModel.logged.observe(this, Observer {
            if (!it){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        })

    }

    override fun onClick(view: View) {
        val id =view.id
        when(id){
            R.id.img_about_toolbar -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
            R.id.img_exit ->{
                exitApp()
            }
        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN ) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                val textSearch = findViewById<EditText>(R.id.edt_item_toolbar)
                searchByName(textSearch.text.toString().toLowerCase())
                return true
            }
        }
        return false
    }

    private fun searchByName(text : String?){
        if (!text.isNullOrEmpty()){
            mainViewModel.getPokemonByName(text)
        }else{
            mainViewModel.getAllPokemons()
        }

    }

    private fun exitApp(){

         AlertDialog.Builder(this)
             .setTitle(R.string.logout)
             .setMessage(R.string.text_logout)
             .setPositiveButton(R.string.yes,DialogInterface.OnClickListener { dialog, id ->
                 mainViewModel.exitApp()
             })
             .setNegativeButton(R.string.cancel, null)
             .show()
    }


}