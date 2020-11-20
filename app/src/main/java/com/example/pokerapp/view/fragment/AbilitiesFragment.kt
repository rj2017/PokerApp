package com.example.pokerapp.view.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokerapp.R
import com.example.pokerapp.constants.PokeConstants
import com.example.pokerapp.view.adapters.AbilitiesAdapter
import com.example.pokerapp.viewmodel.AbilitiesViewModel
import com.example.pokerapp.viewmodel.factory.AbilitiesViewModelFactory


class AbilitiesFragment : Fragment() {

    private lateinit var pokeAbilities: List<String>
    private var idPokemon : Int = 0

    private val abilitiesAdapter : AbilitiesAdapter by lazy {
        AbilitiesAdapter(context!!)
    }

    private val abilitiesViewModel : AbilitiesViewModel by lazy {
        val factory = AbilitiesViewModelFactory(Application())
        ViewModelProvider(this,factory).get(AbilitiesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root =inflater.inflate(R.layout.fragment_abilities, container, false)
        pokeAbilities = arguments?.getSerializable(PokeConstants.PUTEXTRAS.ABILITIES) as List<String>
        idPokemon = arguments?.getInt(PokeConstants.PUTEXTRAS.ID_POKEMON) as Int

        root.findViewById<RecyclerView>(R.id.recycler_abilities).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = abilitiesAdapter
        }

        initList(pokeAbilities)
        observer()

        return root
    }

    private fun initList(lista : List<String>){
        abilitiesViewModel.getAbilities(lista)
    }

    private fun observer(){
        abilitiesViewModel.listAbilities.observe(viewLifecycleOwner, Observer {
            if (it.isSucess()){
                abilitiesAdapter.updateList(it.data!!)
            }else{
                Toast.makeText(context,it.error!!.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}