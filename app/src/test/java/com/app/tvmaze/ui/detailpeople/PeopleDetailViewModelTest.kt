package com.app.tvmaze.ui.detailpeople

import com.app.tvmaze.interfaces.ApiServiceInterface
import com.app.tvmaze.model.people.PeopleShowsModel
import com.app.tvmaze.model.people.ShowPeopleModel
import com.app.tvmaze.model.show.ShowModel
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.RETURNS_DEEP_STUBS
import retrofit2.Response

class PeopleDetailViewModelTest {

    @Mock
    private lateinit var  service: ApiServiceInterface

    @Before
    fun setUp() {

        service = Mockito.mock(ApiServiceInterface::class.java, RETURNS_DEEP_STUBS)
    }

    @Test
    fun getPerson_result_success() {

        Mockito.`when`(service.getPeopleById(1).execute()).thenReturn(Response.success(listOf(
            PeopleShowsModel(show = ShowPeopleModel(
                show = ShowModel(
                    name = "prueba"
                )
            ))
        )))

        val response = service.getPeopleById(1).execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse != null)
        Assert.assertTrue(bodyResponse!!.isNotEmpty())
        Assert.assertTrue(bodyResponse[0].show.show.name == "prueba")
    }

    @Test
    fun getPerson_result_error() {

        Mockito.`when`(service.getPeopleById(1).execute()).thenReturn(Response.error(404,
            ResponseBody.create(null, "Something wrong append")))

        val response = service.getPeopleById(1).execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse == null)
    }
}