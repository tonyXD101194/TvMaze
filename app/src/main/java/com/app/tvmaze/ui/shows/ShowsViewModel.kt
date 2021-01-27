package com.app.tvmaze.ui.shows

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.ApiServiceInterface
import com.app.tvmaze.model.room.FollowSeasonModel
import com.app.tvmaze.model.search.SearchModel
import com.app.tvmaze.model.show.ShowModel
import com.app.tvmaze.repository.FollowRepository
import com.app.tvmaze.utils.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowsViewModel(application: Application): AndroidViewModel(application) {

    private val service: ApiServiceInterface by lazy {

        ApiService.getApiService().create(ApiServiceInterface::class.java)
    }

    private val repository: FollowRepository = FollowRepository(application)

    // region Message Info

    private val messageIntMutable: MutableLiveData<Int> = MutableLiveData()

    val message: LiveData<Int> = this.messageIntMutable

    // endregion


    // region getShows

    private val listMutable: MutableLiveData<List<ShowModel>> = MutableLiveData()

    val list: LiveData<List<ShowModel>> = this.listMutable

    private val listSearchMutable: MutableLiveData<List<ShowModel>> = MutableLiveData()

    val listSearch: LiveData<List<ShowModel>> = this.listSearchMutable

    fun getShows() {

        GlobalScope.launch(Dispatchers.IO) {

            val call: Call<List<ShowModel>> = service.getShows()

            call.enqueue(object: Callback<List<ShowModel>> {

                override fun onResponse(
                    call: Call<List<ShowModel>>?,
                    response: Response<List<ShowModel>>?
                ) {

                    val list = response?.body()

                    if (!list.isNullOrEmpty()) {

                        checkIfShowIsFavorite(
                            list = list
                        )
                    }
                }

                override fun onFailure(call: Call<List<ShowModel>>?, t: Throwable?) {

                    messageIntMutable.postValue(R.string.error_service)
                }
            })
        }
    }

    private fun checkIfShowIsFavorite(list: List<ShowModel>) {

        GlobalScope.launch(Dispatchers.IO) {

            list.forEach {

                var isFavorite = repository.isEpisodeFollowed(it.id)

                if (isFavorite == null) {

                    isFavorite = false
                }

                repository.insertFollow(
                    follow = FollowSeasonModel(
                        id = it.id,
                        favorite = isFavorite
                    )
                )

                it.isFavorite = isFavorite
            }

            listMutable.postValue(list)
        }
    }

    fun setFavorite(id: Int, isFavorite: Boolean) {

        GlobalScope.launch(Dispatchers.IO) {

            repository.setEpisodeFollowed(
                id = id,
                isFavorite = isFavorite
            )
        }
    }

    fun getShowsByName(name: String) {

        GlobalScope.launch(Dispatchers.IO) {

            val call: Call<List<SearchModel>> = service.getShowsByQuery(
                show = name
            )

            call.enqueue(object: Callback<List<SearchModel>> {

                override fun onResponse(
                    call: Call<List<SearchModel>>?,
                    response: Response<List<SearchModel>>?
                ) {

                    val listSearch = response?.body()

                    if (listSearch != null) {

                        val listTemporal: MutableList<ShowModel> = mutableListOf()

                        listSearch.forEach {

                            listTemporal.add(it.show)
                        }

                        listSearchMutable.postValue(listTemporal)
                    }
                }

                override fun onFailure(call: Call<List<SearchModel>>?, t: Throwable?) {

                }
            })
        }
    }

    fun resetListCreated() {

        listMutable.postValue(listMutable.value)
    }

    private val listFavoriteMutable: MutableLiveData<List<ShowModel>> = MutableLiveData()

    val listFavoriteParsed: LiveData<List<ShowModel>> = this.listFavoriteMutable

    fun parseList(listFavorite: List<ShowModel>) {

        GlobalScope.launch(Dispatchers.IO) {

            val listTemporal: MutableList<ShowModel> = mutableListOf()

            listFavorite.forEach {

                if (it.isFavorite) {

                    listTemporal.add(it)
                }
            }

            listTemporal.sortBy { it.name }

            listFavoriteMutable.postValue(listTemporal)
        }
    }

    // endregion
}