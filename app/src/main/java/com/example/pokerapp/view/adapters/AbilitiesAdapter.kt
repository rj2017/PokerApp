package com.example.pokerapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokerapp.R
import com.example.pokerapp.model.AbilitiesPokemonModel
import com.example.pokerapp.view.viewHolder.AbilitiesViewHolder


class AbilitiesAdapter(val context: Context) : RecyclerView.Adapter<AbilitiesViewHolder>(){

    private var myDataset: List<AbilitiesPokemonModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_ablilities_list, parent,false)
        return AbilitiesViewHolder(item,context)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        holder.bindData(myDataset[position])
    }

    fun updateList(list: List<AbilitiesPokemonModel>) {
        myDataset = list
        notifyDataSetChanged()
    }
}