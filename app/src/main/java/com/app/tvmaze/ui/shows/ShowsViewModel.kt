package com.app.tvmaze.ui.shows

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.ApiServiceInterface
import com.app.tvmaze.model.show.ShowModel
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

    // region Message Info

    private val messageIntMutable: MutableLiveData<Int> = MutableLiveData()

    val message: LiveData<Int> = this.messageIntMutable

//    private val messageStringMutable: MutableLiveData<String> = MutableLiveData()
//
//    val messageString: LiveData<String> = this.messageStringMutable

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

                    listMutable.postValue(response?.body())
                }

                override fun onFailure(call: Call<List<ShowModel>>?, t: Throwable?) {

                    messageIntMutable.postValue(R.string.error_service)
                }
            })
        }
    }

    fun getShowsByName(name: String) {

        GlobalScope.launch(Dispatchers.IO) {

            val call: Call<List<ShowModel>> = service.getShowsByQuery(
                show = name
            )

            call.enqueue(object: Callback<List<ShowModel>> {

                override fun onResponse(
                    call: Call<List<ShowModel>>?,
                    response: Response<List<ShowModel>>?
                ) {

                    listSearchMutable.postValue(response?.body())
                }

                override fun onFailure(call: Call<List<ShowModel>>?, t: Throwable?) {

                }
            })
        }
    }

    fun resetListCreated() {

        listMutable.postValue(listMutable.value)
    }

    // endregion
}