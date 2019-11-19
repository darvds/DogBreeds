package com.darvds.dogbreeds.models

import org.junit.Assert.*
import org.junit.Test

class DogBreedsTest {


    @Test
    fun toList_returns_list_alphabetical(){

        val dogBreeds = DogBreeds(
            mapOf(Pair("shiba", null),
                Pair("akita", null)),
            "status"
        )

        val list = dogBreeds.toList()

        assertEquals("akita", list[0].breed)
        assertEquals("shiba", list[1].breed)

    }

    @Test
    fun toList_combines_breed_and_subbreed(){
        val dogBreeds = DogBreeds(
            mapOf(Pair("bulldog", listOf("boston", "english", "french")),
                Pair("chihuahua", null)),
            "status"
        )

        val list = dogBreeds.toList()

        assertEquals(4, list.size)
        assertEquals("bulldog", list[0].breed)
        assertEquals("boston", list[0].subBreed)
        assertEquals("bulldog", list[1].breed)
        assertEquals("english", list[1].subBreed)
        assertEquals("bulldog", list[2].breed)
        assertEquals("french", list[2].subBreed)

    }

}