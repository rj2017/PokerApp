package com.example.pokerapp.view.viewHolder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokerapp.R
import com.example.pokerapp.model.CommunReturnModel
import com.example.pokerapp.util.ImgTypesUtil
import kotlinx.android.synthetic.main.item_simple_list.view.*

class SimpleViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

    private var mImage: ImageView = itemView.findViewById(R.id.img_simple_list)
    private var mText: TextView = itemView.findViewById(R.id.txt_simple_list)

    fun bindData(itens: CommunReturnModel) {

        mImage.setImageResource(ImgTypesUtil.getIdImageForType(itens.name))
        mImage.background.setTint(context.resources.getColor(ImgTypesUtil.getColorForType(itens.name)))

        mText.text = itens.name
    }
}