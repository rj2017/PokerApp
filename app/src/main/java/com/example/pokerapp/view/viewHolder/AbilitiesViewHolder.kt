package com.example.pokerapp.view.viewHolder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokerapp.R
import com.example.pokerapp.model.AbilitiesPokemonModel
import com.example.pokerapp.model.EffectEnTryModel
import kotlinx.android.synthetic.main.item_ablilities_list.view.*

class AbilitiesViewHolder(itemView : View,context: Context) : RecyclerView.ViewHolder(itemView) {

    private val titulo : TextView = itemView.findViewById(R.id.txt_titulo_abilities)
    private val conteudo : TextView = itemView.findViewById(R.id.txt_conteudo_abilities)

    fun bindData(item : AbilitiesPokemonModel){

        titulo.text = item.name
        var abilitiesEnglish = EffectEnTryModel()
        item.effect_entries.forEach {
            if (it.language.name.equals("en")){
                abilitiesEnglish = it
            }
        }

        conteudo.text = abilitiesEnglish.effect
    }


}