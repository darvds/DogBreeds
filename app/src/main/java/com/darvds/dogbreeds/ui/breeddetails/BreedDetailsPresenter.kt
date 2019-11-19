package com.darvds.dogbreeds.ui.breeddetails

import com.darvds.dogbreeds.api.DogBreedsApi
import com.darvds.dogbreeds.models.DogBreed
import com.darvds.dogbreeds.models.DogBreedImages
import com.darvds.dogbreeds.mvp.BasePresenter
import com.darvds.dogbreeds.mvp.BaseView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BreedDetailsPresenter @Inject constructor() : BasePresenter() {

    interface View : BaseView {
        fun setImages(images: List<String>?)
        fun setIsLoading(isLoading: Boolean)
        fun showLoadingError()
    }

    @Inject
    lateinit var api: DogBreedsApi

    private var view: View? = null

    private var images: List<String>? = null


    fun start(breed: DogBreed?) {
        if(breed != null) {
            loadImages(breed)
        }
    }

    /**
     * Load the list of images from the API, or from memory if already downloaded
     */
    private fun loadImages(breed: DogBreed) {

        if (images == null) {
            view?.setIsLoading(true)

            api.getRandomImage(breed.getApiPath()).enqueue(object : Callback<DogBreedImages> {
                override fun onFailure(call: Call<DogBreedImages>, t: Throwable) {
                    view?.setIsLoading(false)
                    view?.showLoadingError()
                }

                override fun onResponse(
                    call: Call<DogBreedImages>,
                    response: Response<DogBreedImages>
                ) {
                    view?.setIsLoading(false)
                    if (response.isSuccessful) {
                        images = response.body()?.message
                        view?.setImages(images)
                    } else {
                        view?.showLoadingError()
                    }
                }
            })
        } else {
            view?.setImages(images)
        }
    }


    fun attachView(view: View) {
        this.view = view
    }


    override fun detachView() {
        this.view = null
    }

}