package com.darvds.dogbreeds.ui.breeddetails

import com.darvds.dogbreeds.api.DogBreedsApi
import com.darvds.dogbreeds.models.DogBreed
import com.darvds.dogbreeds.models.DogBreedImages
import com.darvds.dogbreeds.models.DogBreeds
import com.nhaarman.mockitokotlin2.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class BreedDetailsPresenterTest {

    private val presenter = BreedDetailsPresenter()

    private val mockApi = mock<DogBreedsApi>()

    private val mockView = mock<BreedDetailsPresenter.View>()

    @Before
    fun setUp() {
        presenter.api = mockApi
        presenter.attachView(mockView)
    }

    @Test
    fun start_loads_dog_breeds_and_sets_to_view(){

        val mockBreed = mock<DogBreed>()
        whenever(mockBreed.getApiPath()).thenReturn("mockpath")

        val mockDogBreedImages = mock<DogBreedImages>()
        val mockList = mock<ArrayList<String>>()
        whenever(mockDogBreedImages.message).thenReturn(mockList)

        val mockCall = mock<Call<DogBreedImages>>()

        whenever(mockApi.getRandomImage("mockpath")).thenReturn(mockCall)
        doAnswer { invocationOnMock ->
            val listener = invocationOnMock.arguments[0] as? Callback<DogBreedImages>
            val mockResponse = mock<Response<DogBreedImages>>()
            whenever(mockResponse.isSuccessful).thenReturn(true)
            whenever(mockResponse.body()).thenReturn(mockDogBreedImages)
            listener?.onResponse(mockCall, mockResponse)
        }.whenever(mockCall).enqueue(any())

        presenter.start(mockBreed)

        val inOrder = inOrder(mockView, mockView)
        inOrder.verify(mockView).setIsLoading(true)
        inOrder.verify(mockView).setIsLoading(false)

        verify(mockView).setImages(mockList)

    }

    @Test
    fun start_error_loading_shows_error_on_view(){

        val mockBreed = mock<DogBreed>()
        whenever(mockBreed.getApiPath()).thenReturn("mockpath")

        val mockCall = mock<Call<DogBreedImages>>()

        whenever(mockApi.getRandomImage("mockpath")).thenReturn(mockCall)
        doAnswer { invocationOnMock ->
            val listener = invocationOnMock.arguments[0] as? Callback<DogBreedImages>
            val mockResponse = mock<Response<DogBreedImages>>()
            whenever(mockResponse.isSuccessful).thenReturn(false)
            listener?.onResponse(mockCall, mockResponse)
        }.whenever(mockCall).enqueue(any())

        presenter.start(mockBreed)

        val inOrder = inOrder(mockView, mockView)
        inOrder.verify(mockView).setIsLoading(true)
        inOrder.verify(mockView).setIsLoading(false)

        verify(mockView).showLoadingError()

    }

    @Test
    fun start_failure_shows_error_on_view(){

        val mockBreed = mock<DogBreed>()
        whenever(mockBreed.getApiPath()).thenReturn("mockpath")

        val mockCall = mock<Call<DogBreedImages>>()

        whenever(mockApi.getRandomImage("mockpath")).thenReturn(mockCall)
        doAnswer { invocationOnMock ->
            val listener = invocationOnMock.arguments[0] as? Callback<DogBreedImages>
            listener?.onFailure(mockCall, mock())
        }.whenever(mockCall).enqueue(any())

        presenter.start(mockBreed)


        val inOrder = inOrder(mockView, mockView)
        inOrder.verify(mockView).setIsLoading(true)
        inOrder.verify(mockView).setIsLoading(false)

        verify(mockView).showLoadingError()
    }
}