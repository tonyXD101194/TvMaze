package com.app.tvmaze.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    companion object {

        const val ENDPOINT = "http://api.tvmaze.com"

        private lateinit var retrofit: Retrofit

        fun getApiService(): Retrofit {

            if (!this::retrofit.isInitialized) {

                retrofit = Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }
    }
}