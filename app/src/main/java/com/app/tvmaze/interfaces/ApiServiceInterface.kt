package com.app.tvmaze.interfaces

import com.app.tvmaze.model.episode.EpisodeModel
import com.app.tvmaze.model.people.PeopleModel
import com.app.tvmaze.model.people.PeopleShowsModel
import com.app.tvmaze.model.search.SearchModel
import com.app.tvmaze.model.season.SeasonModel
import com.app.tvmaze.model.show.ShowModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    // region GET

    @GET("/shows?page=1")
    fun getShows(): Call<List<ShowModel>>

    @GET("/search/shows")
    fun getShowsByQuery(@Query("q") show: String): Call<List<SearchModel>>

    @GET("/shows/{id}/seasons")
    fun getSeasonsById(@Path("id") id: Int): Call<List<SeasonModel>>

    @GET("/seasons/{id}/episodes")
    fun getEpisodesBySeason(@Path("id") id: Int): Call<List<EpisodeModel>>

    @GET("/search/people")
    fun getPeopleByName(@Query("q") name: String): Call<List<PeopleModel>>

    @GET("/people/{id}/castcredits?embed=show")
    fun getPeopleById(@Path("id") id: Int): Call<List<PeopleShowsModel>>

    // endregion
}