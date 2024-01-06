package com.coursework.data.responses.sub

import com.google.gson.annotations.SerializedName

data class RedditData(
    @SerializedName("avg_active_users") val avgActiveUsers: Double?,
    @SerializedName("subscribers") val subscribers: Int?
) {

    override fun toString(): String = """
        Average active users on reddit: $avgActiveUsers
        Subscribers on reddit: $subscribers
        """.trimIndent()
}