package com.coursework.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TickersAllCoinsResponse(
    @SerialName("data") val coins: List<CoinData>,
    @SerialName("info") val info: CoinInfo
)

@Serializable
data class CoinData(
    @SerialName("id") val id: String?,
    @SerialName("symbol") val symbol: String?,
    @SerialName("name") val name: String?,
    @SerialName("nameid") val nameId: String?,
    @SerialName("rank") val rank: Int,
    @SerialName("price_usd") val priceUsd: String?,
    @SerialName("percent_change_24h") val percentChange24h: String?,
    @SerialName("percent_change_1h") val percentChange1h: String?,
    @SerialName("percent_change_7d") val percentChange7d: String?,
    @SerialName("price_btc") val priceBtc: String?,
    @SerialName("market_cap_usd") val marketCapUsd: String?,
    @SerialName("volume24") val volume24: Double,
    @SerialName("volume24a") val volume24a: Double,
    @SerialName("csupply") val csupply: String?,
    @SerialName("tsupply") val tsupply: String?,
    @SerialName("msupply") val msupply: String?,
)

@Serializable
data class CoinInfo(
    @SerialName("coins_num") val coinsNum: Int,
    @SerialName("time") val time: Long
)

