package com.darvds.dogbreeds.api

import com.darvds.dogbreeds.models.DogBreedImages
import com.darvds.dogbreeds.models.DogBreeds
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedsApi {

    @GET("breeds/list/all")
    fun getDogBreeds() : Call<DogBreeds>

    @GET("breed/{breed}/images")
    fun getRandomImage(@Path("breed", encoded = true) breed: String) : Call<DogBreedImages>
}