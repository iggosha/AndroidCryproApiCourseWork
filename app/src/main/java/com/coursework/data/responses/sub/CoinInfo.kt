package com.coursework.data.responses.sub

import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("coins_num") val coinsNum: Int,
    @SerializedName("time") val time: Long
)