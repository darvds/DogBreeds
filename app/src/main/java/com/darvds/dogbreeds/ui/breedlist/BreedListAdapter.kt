package com.darvds.dogbreeds.ui.breedlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darvds.dogbreeds.R
import com.darvds.dogbreeds.models.DogBreed
import com.darvds.dogbreeds.models.DogBreeds
import com.darvds.dogbreeds.ui.viewholders.BreedViewHolder

class BreedListAdapter(
    private val context: Context,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<BreedViewHolder>() {

    private var dogBreeds: List<DogBreed>? = null


    /**
     * Set the breeds list and update the recycler view
     */
    fun setBreeds(breeds: List<DogBreed>?) {
        this.dogBreeds = breeds
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_breed, parent, false)
        return BreedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogBreeds?.size ?: 0
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        if (dogBreeds != null) {
            holder.onBind(dogBreeds!![position])
            holder.itemView.setOnClickListener {
                listener.onItemClick(position, holder)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(position: Int, viewHolder: BreedViewHolder)
    }

}
