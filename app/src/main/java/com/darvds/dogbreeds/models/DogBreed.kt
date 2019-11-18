package com.darvds.dogbreeds.models

import android.annotation.SuppressLint

data class DogBreed(val breed: String,
                    val subBreed: String?) {

    @SuppressLint("DefaultLocale")
    fun getName(): String {
        return if(subBreed != null){
            "${subBreed.capitalize()} ${breed.capitalize()}"
        } else {
            breed.capitalize()
        }
    }
}