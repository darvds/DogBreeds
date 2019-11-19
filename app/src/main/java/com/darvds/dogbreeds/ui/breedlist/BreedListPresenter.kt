package com.darvds.dogbreeds.ui.breedlist

import com.darvds.dogbreeds.api.DogBreedsApi
import com.darvds.dogbreeds.models.DogBreed
import com.darvds.dogbreeds.models.DogBreeds
import com.darvds.dogbreeds.mvp.BasePresenter
import com.darvds.dogbreeds.mvp.BaseView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BreedListPresenter @Inject constructor() : BasePresenter() {

    interface View : BaseView {
        fun setBreeds(breeds: List<DogBreed>?)
        fun setIsLoading(isLoading: Boolean)
        fun showLoadingError()
    }

    @Inject
    lateinit var api: DogBreedsApi

    private var view: View? = null

    private var dogBreeds: List<DogBreed>? = null


    fun start() {
        loadDogBreeds()
    }

    /**
     * Load the list of dog breeds from the API, or from memory if already downloaded
     */
    private fun loadDogBreeds() {

        if (dogBreeds == null) {
            view?.setIsLoading(true)

            api.getDogBreeds().enqueue(object : Callback<DogBreeds> {
                override fun onFailure(call: Call<DogBreeds>, t: Throwable) {
                    view?.setIsLoading(false)
                    view?.showLoadingError()
                }

                override fun onResponse(call: Call<DogBreeds>, response: Response<DogBreeds>) {
                    view?.setIsLoading(false)
                    if (response.isSuccessful) {
                        dogBreeds = response.body()?.toList()
                        view?.setBreeds(dogBreeds)
                    } else {
                        view?.showLoadingError()
                    }
                }
            })
        } else {
            view?.setBreeds(dogBreeds)
        }
    }

    /**
     * Get a specific breed for the position in the list
     */
    fun getBreed(position: Int): DogBreed? {
        return dogBreeds?.get(position)
    }


    fun attachView(view: View) {
        this.view = view
    }


    override fun detachView() {
        this.view = null
    }
}