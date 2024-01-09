package com.coursework.data.controller

import com.coursework.data.responses.FetchExchangeDataResponse
import com.coursework.data.responses.MarketForCoinResponse
import com.coursework.data.responses.SocialStatsResponse
import com.coursework.data.responses.TickersAllCoinsResponse
import com.coursework.data.responses.CoinData
import com.coursework.data.responses.ExchangeData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitController {

    @GET("tickers/")
    suspend fun getTickers(): TickersAllCoinsResponse
    @GET("ticker/")
    suspend fun getTickerById(@Query("id") coinId: String): List<CoinData>
    @GET("coin/markets/")
    suspend fun getMarketsForCoinById(@Query("id") coinId: String): List<MarketForCoinResponse>
    @GET("exchange/")
    suspend fun getExchangeDataById(@Query("id") exchangeId: String): FetchExchangeDataResponse
    @GET("exchanges/")
    suspend fun getExchanges(): Map<String, ExchangeData>
    @GET("coin/social_stats/")
    suspend fun getSocialStats(@Query("id") coinId: String): SocialStatsResponse

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