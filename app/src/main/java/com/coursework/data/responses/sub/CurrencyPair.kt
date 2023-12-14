package com.coursework.data.responses.sub

import com.google.gson.annotations.SerializedName

data class CurrencyPair(
    @SerializedName("base") val base: String?,
    @SerializedName("quote") val quote: String?,
    @SerializedName("volume") val volume: Double,
    @SerializedName("price") val price: Double,
    @SerializedName("price_usd") val priceUsd: Double,
    @SerializedName("time") val time: Long
)
