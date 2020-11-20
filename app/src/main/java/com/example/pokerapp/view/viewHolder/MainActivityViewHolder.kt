package com.example.pokerapp.view.viewHolder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokerapp.view.PokemonDetailActivity
import com.example.pokerapp.R
import com.example.pokerapp.constants.PokeConstants
import com.example.pokerapp.model.AbilitiesModel
import com.example.pokerapp.model.MainArrayListModel
import com.example.pokerapp.model.StatusModel
import com.example.pokerapp.util.ImgTypesUtil

class MainActivityViewHolder(itemView: View, val context: Context) :
    RecyclerView.ViewHolder(itemView) {

    private val mLayoutItem: ConstraintLayout = itemView.findViewById(R.id.layout_item_list)
    private val mImagePokemon: ImageView = itemView.findViewById(R.id.img_item)
    private val mNomePokemon: TextView = itemView.findViewById(R.id.txt_nome_item)
    private val mIdPokemon: TextView = itemView.findViewById(R.id.txt_id_item)
    private val mImageTipoPokemon: ImageView = itemView.findViewById(R.id.img_type_item1)
    private val mImageTipoPokemon2: ImageView = itemView.findViewById(R.id.img_type_item2)


    fun bindData(detailPokemon: MainArrayListModel) {

        Glide.with(context).load(getUrlImage(detailPokemon.id)).into(mImagePokemon)

        this.mNomePokemon.text = detailPokemon.name
        this.mIdPokemon.text = "#00${detailPokemon.id}"


        if (detailPokemon.types.size > 1) {
            mImageTipoPokemon.setImageResource(ImgTypesUtil.getIdImageForType(detailPokemon.types[0].type.name))
            mImageTipoPokemon2.setImageResource(ImgTypesUtil.getIdImageForType(detailPokemon.types[1].type.name))
            mImageTipoPokemon2.visibility = View.VISIBLE
            mImageTipoPokemon2.background.setTint(context.resources.getColor(ImgTypesUtil.getColorForType(detailPokemon.types[1].type.name)))
            mImageTipoPokemon.background.setTint(context.resources.getColor(ImgTypesUtil.getColorForType(detailPokemon.types[0].type.name)))
        } else {
            mImageTipoPokemon2.visibility = View.GONE
            mImageTipoPokemon.setImageResource(ImgTypesUtil.getIdImageForType(detailPokemon.types[0].type.name))
            mImageTipoPokemon.background.setTint(context.resources.getColor(ImgTypesUtil.getColorForType(detailPokemon.types[0].type.name)))
        }



        mLayoutItem.setOnClickListener {

            val bundle = Bundle().apply {
                putString(PokeConstants.PUTEXTRAS.IMAGE,getUrlImage(detailPokemon.id))
                putInt(PokeConstants.PUTEXTRAS.ID_POKEMON, detailPokemon.id)
                putSerializable(PokeConstants.PUTEXTRAS.STATS, getStats(detailPokemon.stats))
                putStringArrayList(PokeConstants.PUTEXTRAS.ABILITIES, getAbilities(detailPokemon.abilities))
                putString(PokeConstants.PUTEXTRAS.NAME, detailPokemon.name)
                putString(PokeConstants.PUTEXTRAS.TYPE, detailPokemon.types[0].type.name)
            }
            context.startActivity(Intent(context,
                PokemonDetailActivity::class.java).putExtras(bundle))
        }


    }

    fun getUrlImage(id : Int) = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"

    fun getStats(stats : List<StatusModel>): HashMap<String, Int> {
        var lista = hashMapOf<String,Int>()
        stats.forEach {
            lista[it.stat.name] = it.base_stat
        }
        return lista
    }

    fun getAbilities(abilitiesModel: List<AbilitiesModel>) : ArrayList<String>{
        val lista : ArrayList<String> = arrayListOf()
        abilitiesModel.forEach {
         lista.add(it.communReturn.url)
        }
        return lista
    }
}