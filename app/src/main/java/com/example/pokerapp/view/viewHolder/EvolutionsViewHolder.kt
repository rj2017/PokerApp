package com.example.pokerapp.view.viewHolder

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokerapp.R
import com.example.pokerapp.model.EvolutionSimpleModel
import com.example.pokerapp.util.UrlUtil.getIdForUrl

class EvolutionsViewHolder(item : View, val context : Context) : RecyclerView.ViewHolder(item) {

    private val imagEvolutionFrom : ImageView = item.findViewById(R.id.img_evolution_left)
    private val imagEvolutionTo : ImageView = item.findViewById(R.id.img_evolution_right)

    fun bindData( itemList : EvolutionSimpleModel){

        val imageLeft = getUrlImage(getIdForUrl(itemList.evolutionFrom).replace("/",""))
        val imageRight = getUrlImage(getIdForUrl(itemList.evolutionTo).replace("/",""))

        Glide.with(context).load(imageLeft).into(imagEvolutionFrom)
        Glide.with(context).load(imageRight).into(imagEvolutionTo)
    }

    fun getUrlImage(id : String) = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
}