package com.example.pokerapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokerapp.R
import com.example.pokerapp.model.DetailPokemonModel
import com.example.pokerapp.model.MainArrayListModel
import com.example.pokerapp.view.viewHolder.MainActivityViewHolder

class MainActivityAdapter(val context : Context) : RecyclerView.Adapter<MainActivityViewHolder>(){

    private var myDataset: List<MainArrayListModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
       val item = LayoutInflater.from(parent.context).inflate(R.layout.item_list_all_pokemons, parent,false)

        return MainActivityViewHolder(item,context)

    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.bindData(myDataset[position])
    }

    fun updateList(list: List<MainArrayListModel>) {
        myDataset = list
        notifyDataSetChanged()
    }
}