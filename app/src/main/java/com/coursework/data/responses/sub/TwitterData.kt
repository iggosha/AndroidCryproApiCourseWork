package com.coursework.data.responses.sub

import com.google.gson.annotations.SerializedName

data class TwitterData(
    @SerializedName("followers_count") val followersCount: Int?,
    @SerializedName("status_count") val statusCount: Int?
)
