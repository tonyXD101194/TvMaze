package com.app.tvmaze.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.ApiServiceInterface
import com.app.tvmaze.model.episode.EpisodeModel
import com.app.tvmaze.model.season.SeasonListModel
import com.app.tvmaze.model.season.SeasonModel
import com.app.tvmaze.utils.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailShowViewModel(application: Application): AndroidViewModel(application) {

    private val service: ApiServiceInterface by lazy {

        ApiService.getApiService().create(ApiServiceInterface::class.java)
    }

    // region Message Info

    private val messageIntMutable: MutableLiveData<Int> = MutableLiveData()

    val message: LiveData<Int> = this.messageIntMutable

    // endregion


    // region get Seasons

    private val listSeasonsTemporal: MutableList<SeasonListModel> = mutableListOf()

    fun getSeasons(id: Int) {

        GlobalScope.launch(Dispatchers.IO ) {

            val call: Call<List<SeasonModel>> = service.getSeasonsById(
                id = id
            )

            call.enqueue(object: Callback<List<SeasonModel>> {

                override fun onResponse(
                    call: Call<List<SeasonModel>>?,
                    response: Response<List<SeasonModel>>?
                ) {

                    val listSeasons = response?.body()

                    if (!listSeasons.isNullOrEmpty()) {

                        listSeasonsTemporal.clear()

                        listSeasons.forEach {

                            getEpisodes(it.number)
                        }
                    }
                }

                override fun onFailure(call: Call<List<SeasonModel>>?, t: Throwable?) {

                    messageIntMutable.postValue(R.string.error_service)
                }
            })
        }
    }

    // endregion


    // region get Episodes

    private val listEpisodesMutable: MutableLiveData<List<SeasonListModel>> = MutableLiveData()

    val listSeason: LiveData<List<SeasonListModel>> = this.listEpisodesMutable

    private fun getEpisodes(id: Int) {

        GlobalScope.launch(Dispatchers.IO) {

            val call: Call<List<EpisodeModel>> = service.getEpisodesBySeason(
                id = id
            )

            call.enqueue(object: Callback<List<EpisodeModel>> {

                override fun onResponse(
                    call: Call<List<EpisodeModel>>?,
                    response: Response<List<EpisodeModel>>?
                ) {

                    val list = response?.body()

                    if (!list.isNullOrEmpty()) {

                        listSeasonsTemporal.add(

                            SeasonListModel(
                                numberSeason = id,
                                listEpisodes = list
                            )
                        )

                        listSeasonsTemporal.sortBy { item ->  item.numberSeason }

                        listEpisodesMutable.postValue(listSeasonsTemporal)
                    }
                }

                override fun onFailure(call: Call<List<EpisodeModel>>?, t: Throwable?) {

                }
            })
        }
    }

    // endregion
}