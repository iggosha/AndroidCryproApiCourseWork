package com.coursework.data.controller

import com.coursework.data.responses.FetchExchangeDataResponse
import com.coursework.data.responses.GlobalCryptoDataResponse
import com.coursework.data.responses.MarketForCoinResponse
import com.coursework.data.responses.TickerSpecificCoinResponse
import com.coursework.data.responses.TickersAllCoinsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitController {

    @GET("global/")
    suspend fun getGlobalCryptoData(): List<GlobalCryptoDataResponse>
    @GET("tickers/")
    suspend fun getTickers(): TickersAllCoinsResponse
    @GET("ticker/")
    suspend fun getTickerById(@Query("id") coinId: Int): List<TickerSpecificCoinResponse>
    @GET("coin/markets/")
    suspend fun getMarketsForCoinById(@Query("id") coinId: String): List<MarketForCoinResponse>
    @GET("exchange/")
    suspend fun getExchangeDataById(@Query("id") exchangeId: Int): FetchExchangeDataResponse

    companion object {

        fun getInstance(): RetrofitController {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.coinlore.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitController::class.java)
        }
    }
}