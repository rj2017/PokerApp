package com.example.pokerapp.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pokerapp.R


class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val pInfo = packageManager.getPackageInfo(packageName, 0)
        val version = pInfo.versionName

        val textVersion = findViewById<TextView>(R.id.txt_about_version)
        textVersion.text = version

        initActionBar()
    }

    private fun initActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar_about))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}