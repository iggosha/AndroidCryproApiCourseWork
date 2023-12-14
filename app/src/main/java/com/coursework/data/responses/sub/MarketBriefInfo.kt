package com.coursework.data.responses.sub

import com.google.gson.annotations.SerializedName

data class MarketBriefInfo(
    @SerializedName("name") val name: String?,
    @SerializedName("date_live") val dateLive: String?,
    @SerializedName("url") val url: String?
)