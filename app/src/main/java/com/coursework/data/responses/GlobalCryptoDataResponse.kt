package com.coursework.data.responses

import com.google.gson.annotations.SerializedName

data class GlobalCryptoDataResponse(
    @SerializedName("coins_count") val coinsCount: Int,
    @SerializedName("active_markets") val activeMarkets: Int,
    @SerializedName("total_mcap") val totalMcap: Double,
    @SerializedName("total_volume") val totalVolume: Double,
    @SerializedName("btc_d") val btcD: String,
    @SerializedName("eth_d") val ethD: String,
    @SerializedName("mcap_change") val mcapChange: String,
    @SerializedName("volume_change") val volumeChange: String,
    @SerializedName("avg_change_percent") val avgChangePercent: String,
    @SerializedName("volume_ath") val volumeAth: Long,
    @SerializedName("mcap_ath") val mcapAth: Double
)
