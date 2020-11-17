package com.example.pokerapp.view

import android.app.ActionBar
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.pokerapp.R
import com.example.pokerapp.constants.PokeConstants
import com.example.pokerapp.util.ImgTypesUtil
import com.example.pokerapp.view.adapters.TabsAdapter
import com.example.pokerapp.view.fragment.AbilitiesFragment
import com.example.pokerapp.view.fragment.EvolutionsFragment
import com.example.pokerapp.view.fragment.StatsFragment
import com.example.pokerapp.viewmodel.PokemonDetailViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import kotlinx.android.synthetic.main.item_detail_toolbar.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailActivity : AppCompatActivity() {

    private var idPokemon : Int = 0
    private var image : String = ""
    private var namePokemon : String = ""
    private var typePokemon : String = ""

    private var bundle : Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bundle = intent.extras
        initBundle()
        initTheme(typePokemon)
        setContentView(R.layout.activity_pokemon_detail)

        initBundle()
        initTheme(typePokemon)

        setTabsbar()
        setActionbar()

        img_back_toolbar.setOnClickListener {
            finish()
        }
    }


    private fun initBundle(){
        if(isBundle()){
            idPokemon = bundle!!.getInt(PokeConstants.PUTEXTRAS.ID_POKEMON)
            image = bundle!!.getString(PokeConstants.PUTEXTRAS.IMAGE) ?: ""
            namePokemon = bundle!!.getString(PokeConstants.PUTEXTRAS.NAME) ?: ""
            typePokemon = bundle!!.getString(PokeConstants.PUTEXTRAS.TYPE) ?: ""
        }
    }


    private fun initTheme(type : String){
        val theme = ImgTypesUtil.getThemeForType(type)
        this.setTheme(theme)
    }


    private fun isBundle() = bundle != null

    private fun setTabsbar(){
        val tabs = findViewById<TabLayout>(R.id.tab_pokemon_detail)
        val viewPager = findViewById<ViewPager>(R.id.view_page)
        val adapter = TabsAdapter(this,supportFragmentManager,bundle!!)

        adapter.add(StatsFragment(),"Stats")
        adapter.add(EvolutionsFragment(),"Evolution")
        adapter.add(AbilitiesFragment(),"Abilities")

        viewPager.adapter =adapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun setActionbar(){
        val actionBar : ActionBar? = actionBar
        actionBar?.let {
            it.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            it.setCustomView(R.layout.item_detail_toolbar)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
            it.show()
        }

        if (isBundle()){
            val img = findViewById<ImageView>(R.id.img_detail_toolbar)
            val name = findViewById<TextView>(R.id.txt_name_detail_toolbar)
            val id = findViewById<TextView>(R.id.txt_id_detail_toolbar)
            val type = findViewById<TextView>(R.id.txt_type_detail_toolbar1)
            val imgType = findViewById<ImageView>(R.id.img_type_detail_toolbar1)

            Glide.with(this).load(image).into(img)
            id.text = "#${idPokemon.toString()}"
            name.text = namePokemon
            type.text = typePokemon
            imgType.setImageResource(ImgTypesUtil.getIdImageForType(typePokemon))
        }

    }


}