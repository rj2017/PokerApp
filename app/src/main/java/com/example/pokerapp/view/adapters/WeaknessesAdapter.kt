package com.example.pokerapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokerapp.R
import com.example.pokerapp.model.DamageModel
import com.example.pokerapp.model.MainArrayListModel
import com.example.pokerapp.view.viewHolder.MainActivityViewHolder
import com.example.pokerapp.view.viewHolder.SimpleViewHolder

class WeaknessesAdapter(val context: Context) : RecyclerView.Adapter<SimpleViewHolder>() {

    private var myDataset: DamageModel = DamageModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_simple_list, parent,false)

        return SimpleViewHolder(item,context)
    }

    override fun getItemCount(): Int {
        return myDataset.damage_relations.doubleDamageFrom.size
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bindData(myDataset.damage_relations.doubleDamageFrom[position])
    }

    fun updateList(list: DamageModel) {
        myDataset = list
        notifyDataSetChanged()
    }
}