package com.darvds.dogbreeds.di.modules

import com.darvds.dogbreeds.api.DogBreedsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkingModule {

    @Provides
    @Singleton
    fun provideApi() : DogBreedsApi {
        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(DogBreedsApi::class.java)
    }
}