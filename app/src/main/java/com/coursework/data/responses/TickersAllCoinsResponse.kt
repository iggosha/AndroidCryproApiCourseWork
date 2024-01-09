package com.coursework.data.responses

import com.coursework.data.responses.sub.CoinInfo
import com.google.gson.annotations.SerializedName

data class TickersAllCoinsResponse(
    @SerializedName("data") val coins: List<CoinData>,
    @SerializedName("info") val info: CoinInfo
)