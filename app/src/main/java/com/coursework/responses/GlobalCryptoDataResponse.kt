package com.coursework.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GlobalCryptoDataResponse(
    @SerialName("coins_count") val coinsCount: Int,
    @SerialName("active_markets") val activeMarkets: Int,
    @SerialName("total_mcap") val totalMcap: Double,
    @SerialName("total_volume") val totalVolume: Double,
    @SerialName("btc_d") val btcD: String,
    @SerialName("eth_d") val ethD: String,
    @SerialName("mcap_change") val mcapChange: String,
    @SerialName("volume_change") val volumeChange: String,
    @SerialName("avg_change_percent") val avgChangePercent: String,
    @SerialName("volume_ath") val volumeAth: Long,
    @SerialName("mcap_ath") val mcapAth: Double
)
