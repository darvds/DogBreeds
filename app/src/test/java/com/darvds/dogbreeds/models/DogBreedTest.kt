package com.darvds.dogbreeds.models

import org.junit.Assert.*
import org.junit.Test

class DogBreedTest{

    @Test
    fun getName_returns_capitalized(){
        val dogBreed = DogBreed("chow", null)
        assertEquals("Chow", dogBreed.getName())
    }


    @Test
    fun getName_returns_capitalized_with_subbreed_first(){
        val dogBreed = DogBreed("dane", "great")
        assertEquals("Great Dane", dogBreed.getName())
    }

    @Test
    fun getApi_returns_lowercase(){
        val dogBreed = DogBreed("Chow", null)
        assertEquals("chow", dogBreed.getApiPath())
    }


    @Test
    fun getApi_returns_lowercase_with_path(){
        val dogBreed = DogBreed("Dane", "Great")
        assertEquals("dane/great", dogBreed.getApiPath())
    }


}