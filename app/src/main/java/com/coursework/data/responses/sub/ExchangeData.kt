package com.coursework.data.responses.sub

import com.google.gson.annotations.SerializedName

data class ExchangeData(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("name_id") val nameId: String,
    @SerializedName("volume_usd") val volumeUsd: Double,
    @SerializedName("active_pairs") val activePairs: Int,
    @SerializedName("url") val url: String,
    @SerializedName("country") val country: String
)
{

    override fun toString(): String =
        """$id $name $nameId $volumeUsd $activePairs $url $country
            
        """.trimIndent()
}