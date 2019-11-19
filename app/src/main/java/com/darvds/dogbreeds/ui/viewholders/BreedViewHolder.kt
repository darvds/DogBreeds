package com.darvds.dogbreeds.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.darvds.dogbreeds.models.DogBreed
import kotlinx.android.synthetic.main.item_breed.view.*

class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(breed: DogBreed) {
        itemView.item_breed_name_textview.text = breed.getName()
    }
}