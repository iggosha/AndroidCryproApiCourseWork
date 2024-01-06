package com.coursework.data.responses

import com.coursework.data.responses.sub.CurrencyPair
import com.coursework.data.responses.sub.MarketBriefInfo
import com.google.gson.annotations.SerializedName

data class FetchExchangeDataResponse(
    @SerializedName("0") val emptyNameField: MarketBriefInfo?,
    @SerializedName("pairs") val currencyPairs: List<CurrencyPair>
)