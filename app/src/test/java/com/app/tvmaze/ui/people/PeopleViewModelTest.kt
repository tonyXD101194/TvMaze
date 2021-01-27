package com.app.tvmaze.ui.people

import com.app.tvmaze.interfaces.ApiServiceInterface
import com.app.tvmaze.model.people.PeopleModel
import com.app.tvmaze.model.people.PersonModel
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response

class PeopleViewModelTest {

    @Mock
    private lateinit var  service: ApiServiceInterface

    @Before
    fun setUp() {

        service = Mockito.mock(ApiServiceInterface::class.java, Mockito.RETURNS_DEEP_STUBS)
    }

    @Test
    fun getPeople_result_success() {

        Mockito.`when`(service.getPeopleByName("lauren").execute()).thenReturn(
            Response.success(listOf(
                PeopleModel(person = PersonModel(name = "lauren"))
            )))

        val response = service.getPeopleByName("lauren").execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse != null)
        Assert.assertTrue(bodyResponse!!.isNotEmpty())
        Assert.assertTrue(bodyResponse[0].person.name == "lauren")
    }

    @Test
    fun getPeople_result_error() {

        Mockito.`when`(service.getPeopleByName("lauren").execute()).thenReturn(
            Response.error(404,
                ResponseBody.create(null, "Something wrong append")))

        val response = service.getPeopleByName("lauren").execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse == null)
    }
}