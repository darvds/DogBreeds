package com.darvds.dogbreeds.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DogBreed(
    val breed: String,
    val subBreed: String?
) : Parcelable {

    /**
     * Get the name of the breed including the sub-breed where applicable
     */
    @SuppressLint("DefaultLocale")
    fun getName(): String {
        return if (subBreed != null) {
            "${subBreed.capitalize()} ${breed.capitalize()}"
        } else {
            breed.capitalize()
        }
    }

    /**
     * Get the API path for the breed
     */
    @SuppressLint("DefaultLocale")
    fun getApiPath(): String {
        return if (subBreed != null) {
            "${breed.toLowerCase()}/${subBreed.toLowerCase()}"
        } else {
            breed.toLowerCase()
        }
    }
}