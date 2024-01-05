package com.coursework.data.responses

import com.google.gson.annotations.SerializedName

data class MarketForCoinResponse(
    @SerializedName("name") val name: String,
    @SerializedName("base") val base: String,
    @SerializedName("quote") val quote: String,
    @SerializedName("price") val price: Double,
    @SerializedName("price_usd") val priceUsd: Double,
    @SerializedName("volume") val volume: Double,
    @SerializedName("volume_usd") val volumeUsd: Double,
    @SerializedName("time") val time: Long
) {

    override fun toString(): String =
        """
            name $name
            base $base
            quote $quote
            price $price
            price$ $priceUsd$ 
            volume $volume 
            volume$ $volumeUsd 
            time $time
        """.trimIndent()
}