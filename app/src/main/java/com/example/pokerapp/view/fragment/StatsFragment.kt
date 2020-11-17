package com.example.pokerapp.view.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokerapp.R
import com.example.pokerapp.constants.PokeConstants
import com.example.pokerapp.view.adapters.ResistencesAdapter
import com.example.pokerapp.view.adapters.WeaknessesAdapter
import com.example.pokerapp.viewmodel.StatsViewModel
import com.example.pokerapp.viewmodel.factory.StatsViewModelFactory



class StatsFragment : Fragment() {

    private lateinit var pokeStats: HashMap<String, Int>
    private var idPokemon : Int = 0

    private val statsViewModel by lazy {
        val factory = StatsViewModelFactory(Application())
        ViewModelProvider(this,factory).get(StatsViewModel::class.java)
    }

    private val weaknessesAdapter : WeaknessesAdapter by lazy {
        WeaknessesAdapter(context!!)
    }

    private val resistencesAdapter : ResistencesAdapter by lazy {
        ResistencesAdapter(context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_stats, container, false)

        pokeStats = arguments?.getSerializable(PokeConstants.PUTEXTRAS.STATS) as HashMap<String, Int>
        idPokemon = arguments?.getInt(PokeConstants.PUTEXTRAS.ID_POKEMON) as Int

        root.findViewById<RecyclerView>(R.id.recycler_weaknesses).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = weaknessesAdapter
        }
        root.findViewById<RecyclerView>(R.id.recycler_resistences).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = resistencesAdapter
        }

        initStats(pokeStats, root)
        initLists(idPokemon)
        observer()

        return root
    }

    private fun initLists(id : Int){
        statsViewModel.getDamageById(id)
    }

    private fun initStats(stats: HashMap<String, Int>, view: View) {


        var hp = view.findViewById<ProgressBar>(R.id.power_total_stats_hp)
        var atk = view.findViewById<ProgressBar>(R.id.power_total_stats_atk)
        var def = view.findViewById<ProgressBar>(R.id.power_total_stats_def)
        var stak = view.findViewById<ProgressBar>(R.id.power_total_stats_satk)
        var sdef = view.findViewById<ProgressBar>(R.id.power_total_stats_sdef)
        var spd = view.findViewById<ProgressBar>(R.id.power_total_stats_spd)

        var hpText = view.findViewById<TextView>(R.id.value_status_hp)
        var atkText = view.findViewById<TextView>(R.id.value_status_atk)
        var defText = view.findViewById<TextView>(R.id.value_status_def)
        var stakText = view.findViewById<TextView>(R.id.value_status_satk)
        var sdefText = view.findViewById<TextView>(R.id.value_status_sdef)
        var spdText = view.findViewById<TextView>(R.id.value_status_spd)

        hp.progress = stats.getValue("hp")
        hpText.text = stats.getValue("hp").toString()

        atk.progress = stats.getValue("attack")
        atkText.text = stats.getValue("attack").toString()

        def.progress = stats.getValue("defense")
        defText.text = stats.getValue("defense").toString()

        stak.progress = stats.getValue("special-attack")
        stakText.text = stats.getValue("special-attack").toString()

        sdef.progress = stats.getValue("special-defense")
        sdefText.text = stats.getValue("special-defense").toString()

        spd.progress = stats.getValue("speed")
        spdText.text = stats.getValue("speed").toString()

    }

    private fun observer(){
        statsViewModel.listDamage.observe(viewLifecycleOwner, Observer {
            if (it.isSucess()){
                if (it.data!!.damage_relations.doubleDamageFrom.size > 0) weaknessesAdapter.updateList(it.data)
                if (it.data.damage_relations.doubleDamageTo.size > 0) resistencesAdapter.updateList(it.data)
            }else{
                Toast.makeText(context,it.error!!.message,Toast.LENGTH_SHORT).show()
            }
        })
    }

}