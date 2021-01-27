package com.app.tvmaze.ui.people

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.tvmaze.R
import com.app.tvmaze.interfaces.ApiServiceInterface
import com.app.tvmaze.model.people.PeopleModel
import com.app.tvmaze.utils.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleViewModel(application: Application): AndroidViewModel(application) {

    private val service: ApiServiceInterface by lazy {

        ApiService.getApiService().create(ApiServiceInterface::class.java)
    }

    // region Message Info

    private val messageIntMutable: MutableLiveData<Int> = MutableLiveData()

    val message: LiveData<Int> = this.messageIntMutable

    // endregion


    // region list people

    private val listMutable: MutableLiveData<List<PeopleModel>> = MutableLiveData()

    val list: LiveData<List<PeopleModel>> = this.listMutable

    fun getPeople(query: String) {

        GlobalScope.launch(Dispatchers.IO) {

            val call: Call<List<PeopleModel>> = service.getPeopleByName(
                name = query
            )

            call.enqueue(object: Callback<List<PeopleModel>> {

                override fun onResponse(
                    call: Call<List<PeopleModel>>?,
                    response: Response<List<PeopleModel>>?
                ) {

                    listMutable.postValue(response?.body())
                }

                override fun onFailure(call: Call<List<PeopleModel>>?, t: Throwable?) {

                    messageIntMutable.postValue(R.string.error_service)
                }
            })
        }
    }

    // endregion
}