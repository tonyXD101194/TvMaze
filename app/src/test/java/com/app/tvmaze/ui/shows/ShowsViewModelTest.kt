package com.app.tvmaze.ui.shows

import com.app.tvmaze.interfaces.ApiServiceInterface
import com.app.tvmaze.model.search.SearchModel
import com.app.tvmaze.model.show.ShowModel
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response

class ShowsViewModelTest {

    @Mock
    private lateinit var  service: ApiServiceInterface

    @Before
    fun setUp() {

        service = Mockito.mock(ApiServiceInterface::class.java, Mockito.RETURNS_DEEP_STUBS)
    }

    @Test
    fun getShows_result_success() {

        Mockito.`when`(service.getShows().execute()).thenReturn(
            Response.success(listOf(
                ShowModel(name = "prueba")
            )))

        val response = service.getShows().execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse != null)
        Assert.assertTrue(bodyResponse!!.isNotEmpty())
        Assert.assertTrue(bodyResponse[0].name == "prueba")
    }

    @Test
    fun getShows_result_error() {

        Mockito.`when`(service.getShows().execute()).thenReturn(
            Response.error(404,
                ResponseBody.create(null, "Something wrong append")))

        val response = service.getShows().execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse == null)
    }

    @Test
    fun getShowsByName_result_success() {

        Mockito.`when`(service.getShowsByQuery("girls").execute()).thenReturn(
            Response.success(listOf(
                SearchModel(show = ShowModel(name = "girls something"))
            )))

        val response = service.getShowsByQuery("girls").execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse != null)
        Assert.assertTrue(bodyResponse!!.isNotEmpty())
        Assert.assertTrue(bodyResponse[0].show.name == "girls something")
    }

    @Test
    fun getShowsByName_result_error() {

        Mockito.`when`(service.getShowsByQuery("girls").execute()).thenReturn(
            Response.error(404,
                ResponseBody.create(null, "Something wrong append")))

        val response = service.getShowsByQuery("girls").execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse == null)
    }
}