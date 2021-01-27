package com.app.tvmaze.ui.detail

import com.app.tvmaze.interfaces.ApiServiceInterface
import com.app.tvmaze.model.season.SeasonModel
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.RETURNS_DEEP_STUBS
import retrofit2.Response

class DetailShowViewModelTest {

    @Mock
    private lateinit var  service: ApiServiceInterface

    @Before
    fun setUp() {

        //MockitoAnnotations.initMocks(this)
        service = Mockito.mock(ApiServiceInterface::class.java, RETURNS_DEEP_STUBS)
    }

    @Test
    fun getSeasons_result_success() {

        Mockito.`when`(service.getSeasonsById(1).execute()).thenReturn(Response.success(listOf(
            SeasonModel(name = "prueba")
        )))

        val response = service.getSeasonsById(1).execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse != null)
        Assert.assertTrue(bodyResponse!!.isNotEmpty())
        Assert.assertTrue(bodyResponse[0].name == "prueba")
    }

    @Test
    fun getSeasons_result_error() {

        Mockito.`when`(service.getSeasonsById(1).execute()).thenReturn(Response.error(404,
            ResponseBody.create(null, "Something wrong append")))

        val response = service.getSeasonsById(1).execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse == null)
    }
}