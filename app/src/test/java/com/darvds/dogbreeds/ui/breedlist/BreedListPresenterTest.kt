package com.darvds.dogbreeds.ui.breedlist

import com.darvds.dogbreeds.api.DogBreedsApi
import com.darvds.dogbreeds.models.DogBreed
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
class BreedListPresenterTest {

    private val presenter = BreedListPresenter()

    private val mockApi = mock<DogBreedsApi>()

    private val mockView = mock<BreedListPresenter.View>()

    @Before
    fun setUp() {
        presenter.api = mockApi
        presenter.attachView(mockView)
    }

    @Test
    fun start_loads_dog_breeds_and_sets_to_view(){
        val mockDogBreeds = mock<DogBreeds>()
        val mockList = mock<ArrayList<DogBreed>>()
        whenever(mockDogBreeds.toList()).thenReturn(mockList)

        val mockCall = mock<Call<DogBreeds>>()

        whenever(mockApi.getDogBreeds()).thenReturn(mockCall)
        doAnswer { invocationOnMock ->
            val listener = invocationOnMock.arguments[0] as? Callback<DogBreeds>
            val mockResponse = mock<Response<DogBreeds>>()
            whenever(mockResponse.isSuccessful).thenReturn(true)
            whenever(mockResponse.body()).thenReturn(mockDogBreeds)
            listener?.onResponse(mockCall, mockResponse)
        }.whenever(mockCall).enqueue(any())

        presenter.start()

        val inOrder = inOrder(mockView, mockView)
        inOrder.verify(mockView).setIsLoading(true)
        inOrder.verify(mockView).setIsLoading(false)

        verify(mockView).setBreeds(mockList)

    }

    @Test
    fun start_error_loading_shows_error_on_view(){
        val mockCall = mock<Call<DogBreeds>>()

        whenever(mockApi.getDogBreeds()).thenReturn(mockCall)
        doAnswer { invocationOnMock ->
            val listener = invocationOnMock.arguments[0] as? Callback<DogBreeds>
            val mockResponse = mock<Response<DogBreeds>>()
            whenever(mockResponse.isSuccessful).thenReturn(false)
            listener?.onResponse(mockCall, mockResponse)
        }.whenever(mockCall).enqueue(any())

        presenter.start()

        val inOrder = inOrder(mockView, mockView)
        inOrder.verify(mockView).setIsLoading(true)
        inOrder.verify(mockView).setIsLoading(false)

        verify(mockView).showLoadingError()

    }

    @Test
    fun start_failure_shows_error_on_view(){
        val mockCall = mock<Call<DogBreeds>>()

        whenever(mockApi.getDogBreeds()).thenReturn(mockCall)
        doAnswer { invocationOnMock ->
            val listener = invocationOnMock.arguments[0] as? Callback<DogBreeds>
            listener?.onFailure(mockCall, mock())
        }.whenever(mockCall).enqueue(any())

        presenter.start()

        val inOrder = inOrder(mockView, mockView)
        inOrder.verify(mockView).setIsLoading(true)
        inOrder.verify(mockView).setIsLoading(false)

        verify(mockView).showLoadingError()
    }
}