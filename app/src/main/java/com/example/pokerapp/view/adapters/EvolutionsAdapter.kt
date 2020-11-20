package com.example.pokerapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokerapp.R
import com.example.pokerapp.model.AbilitiesPokemonModel
import com.example.pokerapp.model.CommunReturnModel
import com.example.pokerapp.model.EvolutionSimpleModel
import com.example.pokerapp.view.viewHolder.AbilitiesViewHolder
import com.example.pokerapp.view.viewHolder.EvolutionsViewHolder

class EvolutionsAdapter(val context : Context): RecyclerView.Adapter<EvolutionsViewHolder>() {

    private var myDataset: List<EvolutionSimpleModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionsViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_evolution_list, parent,false)
        return EvolutionsViewHolder(item,context)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: EvolutionsViewHolder, position: Int) {
        return holder.bindData(myDataset[position])
    }

    fun updateList(list: List<EvolutionSimpleModel>) {
        myDataset = list
        notifyDataSetChanged()
    }
}