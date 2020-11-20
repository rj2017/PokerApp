package com.example.pokerapp.view.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokerapp.R
import com.example.pokerapp.constants.PokeConstants
import com.example.pokerapp.model.CommunReturnModel
import com.example.pokerapp.model.EvolutionModel
import com.example.pokerapp.model.EvolutionSimpleModel
import com.example.pokerapp.view.adapters.EvolutionsAdapter
import com.example.pokerapp.viewmodel.AbilitiesViewModel
import com.example.pokerapp.viewmodel.EvolutionsViewModel
import com.example.pokerapp.viewmodel.factory.AbilitiesViewModelFactory
import com.example.pokerapp.viewmodel.factory.EvolutionsViewModelFactory

class EvolutionsFragment : Fragment() {

    private var idPokemon: Int = 0

    private val evolutionsViewModel: EvolutionsViewModel by lazy {
        val factory = EvolutionsViewModelFactory(Application())
        ViewModelProvider(this, factory).get(EvolutionsViewModel::class.java)
    }

    private val evolutionAdapter: EvolutionsAdapter by lazy {
        EvolutionsAdapter(context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_evolutions, container, false)
        idPokemon = arguments?.getInt(PokeConstants.PUTEXTRAS.ID_POKEMON) as Int

        root.findViewById<RecyclerView>(R.id.recycler_evolution).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = evolutionAdapter

        }
        initEvolutionsList()
        observer()
        return root
    }

    private fun observer() {
        evolutionsViewModel.listEvolution.observe(viewLifecycleOwner, Observer {
            if (it.isSucess()) {
                evolutionAdapter.updateList(mapEvolutionList(it.data!!))
            } else {
                Toast.makeText(context, it.error!!.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initEvolutionsList() {
        evolutionsViewModel.getEvolution(idPokemon)
    }

    private fun mapEvolutionList(evolution: EvolutionModel): List<EvolutionSimpleModel> {
        val list = mutableListOf<EvolutionSimpleModel>()

        list.add(
            EvolutionSimpleModel(
                evolution.chain.species.url,
                evolution.chain.evolves_to[0].species.url
            )
        )

        if (!evolution.chain.evolves_to[0].evolves_to.isEmpty()) {
            list.add(
                EvolutionSimpleModel(
                    evolution.chain.evolves_to[0].species.url,
                    evolution.chain.evolves_to[0].evolves_to[0].species.url
                )
            )
        }
        return list
    }


}