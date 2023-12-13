package com.coursework.data.controller

import com.coursework.data.baseUrl
import com.coursework.data.responses.GlobalCryptoDataResponse
import com.coursework.data.responses.TickersAllCoinsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitController {

    @GET("global/")
    suspend fun getGlobalCryptoData(): List<GlobalCryptoDataResponse>

    @GET("tickers/")
    suspend fun getTickers(): TickersAllCoinsResponse

    companion object {

        fun getInstance(): RetrofitController {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitController::class.java)
        }
    }
}