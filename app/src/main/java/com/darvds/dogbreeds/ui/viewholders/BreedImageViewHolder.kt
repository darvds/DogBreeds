package com.darvds.dogbreeds.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darvds.dogbreeds.models.DogBreed
import kotlinx.android.synthetic.main.item_breed.*
import kotlinx.android.synthetic.main.item_breed.view.*
import kotlinx.android.synthetic.main.item_breed_image.view.*

class BreedImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(url: String){
        Glide.with(itemView.item_breed_imageview)
            .load(url)
            .into(itemView.item_breed_imageview)
    }
}