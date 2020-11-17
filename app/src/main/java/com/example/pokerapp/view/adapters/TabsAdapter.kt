package com.example.pokerapp.view.adapters

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pokerapp.model.DetailPokemonModel
import com.example.pokerapp.model.StatusModel
import com.example.pokerapp.view.fragment.AbilitiesFragment
import com.example.pokerapp.view.fragment.EvolutionsFragment
import com.example.pokerapp.view.fragment.StatsFragment

class TabsAdapter(var context: Context, fm : FragmentManager,var bundle: Bundle) : FragmentPagerAdapter(fm) {

    private val fragments = mutableListOf<Fragment>()
    private val titulosAbas = mutableListOf<String>()

    fun add(fragment: Fragment,titulos : String){
        fragments.add(fragment)
        titulosAbas.add(titulos)
    }

    override fun getItem(position: Int): Fragment {
        val fragmentReturn = fragments[position]
        fragmentReturn.arguments = bundle
        return fragmentReturn
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titulosAbas[position]
    }
}