package com.coursework.data.responses

import com.google.gson.annotations.SerializedName

data class AllExchangesResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("name_id") val nameId: String,
    @SerializedName("volume_usd") val volumeUsd: Double,
    @SerializedName("active_pairs") val activePairs: Int,
    @SerializedName("url") val url: String,
    @SerializedName("country") val country: String
)