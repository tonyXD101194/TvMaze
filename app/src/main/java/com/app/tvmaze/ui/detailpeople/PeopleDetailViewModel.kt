package com.app.tvmaze.ui.detailpeople

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.ApiServiceInterface
import com.app.tvmaze.model.people.PeopleShowsModel
import com.app.tvmaze.utils.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleDetailViewModel(application: Application): AndroidViewModel(application) {

    private val service: ApiServiceInterface by lazy {

        ApiService.getApiService().create(ApiServiceInterface::class.java)
    }

    // region Message Info

    private val messageIntMutable: MutableLiveData<Int> = MutableLiveData()

    val message: LiveData<Int> = this.messageIntMutable

    // endregion


    // region people

    private val listShowsMutable: MutableLiveData<List<PeopleShowsModel>> = MutableLiveData()

    val listShows: LiveData<List<PeopleShowsModel>> = this.listShowsMutable

    fun getPerson(id: Int) {

        GlobalScope.launch(Dispatchers.IO) {

            val call: Call<List<PeopleShowsModel>> = service.getPeopleById(
                id = id
            )

            call.enqueue(object: Callback<List<PeopleShowsModel>> {

                override fun onResponse(
                    call: Call<List<PeopleShowsModel>>?,
                    response: Response<List<PeopleShowsModel>>?
                ) {

                    listShowsMutable.postValue(response?.body())
                }

                override fun onFailure(call: Call<List<PeopleShowsModel>>?, t: Throwable?) {

                    messageIntMutable.postValue(R.string.error_service)
                }
            })
        }
    }

    // endregion
}