package com.coursework.data.responses

import com.coursework.data.responses.sub.RedditData
import com.coursework.data.responses.sub.TwitterData
import com.google.gson.annotations.SerializedName

data class SocialStatsResponse(
    @SerializedName("reddit") val redditData: RedditData?,
    @SerializedName("twitter") val twitterData: TwitterData?
) {

    override fun toString() =
        redditData.toString() + '\n' + twitterData.toString()
}