package com.darvds.dogbreeds.mvp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PresenterStorageTest {

    @Test
    fun putPresenter_returns_correctly(){

        val presenter = MockPresenter1()

        PresenterStorage.putPresenter("presenterid", presenter)

        assertEquals(PresenterStorage.getPresenter("presenterid"), presenter)
    }

    @Test
    fun getPresenter_return_wrong_type_returns_null(){

        val presenter = MockPresenter1()

        PresenterStorage.putPresenter("presenterid", presenter)

        val presenter2: MockPresenter2? = PresenterStorage.getPresenter("presenterid")

        assertNull(presenter2)
    }

    @Test
    fun getPresenter_not_exit_returns_null(){
        assertNull(PresenterStorage.getPresenter("notexist"))
    }


    class MockPresenter1 : BasePresenter()

    class MockPresenter2 : BasePresenter()
}