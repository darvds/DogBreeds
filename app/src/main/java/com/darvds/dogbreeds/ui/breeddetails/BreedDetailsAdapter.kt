package com.darvds.dogbreeds.ui.breeddetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darvds.dogbreeds.R
import com.darvds.dogbreeds.ui.viewholders.BreedImageViewHolder

class BreedDetailsAdapter(private val context: Context) :
    RecyclerView.Adapter<BreedImageViewHolder>() {

    private var images: List<String>? = null

    /**
     * Set the image list and update the recycler view
     */
    fun setImages(images: List<String>?) {
        this.images = images
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_breed_image, parent, false)
        return BreedImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images?.size ?: 0
    }

    override fun onBindViewHolder(holder: BreedImageViewHolder, position: Int) {
        if (images != null) {
            holder.onBind(images!![position])
        }
    }

}
