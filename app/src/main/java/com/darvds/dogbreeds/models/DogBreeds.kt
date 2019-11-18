package com.darvds.dogbreeds.models

data class DogBreeds(
    val message: Map<String, List<String>?>,
    val status: String) {

    /**
     * Get a list of each possible dog breed, including their subbreeds,
     * in alphabetical order
     */
    fun toList(): ArrayList<DogBreed> {
        val dogBreeds = ArrayList<DogBreed>()
        message.toSortedMap().forEach { breed ->
            if(!breed.value.isNullOrEmpty()){
                breed.value?.forEach { subBreed ->
                    dogBreeds.add(DogBreed(breed.key, subBreed))
                }
            } else {
                dogBreeds.add(DogBreed(breed.key, null))
            }
        }
        return dogBreeds
    }
}