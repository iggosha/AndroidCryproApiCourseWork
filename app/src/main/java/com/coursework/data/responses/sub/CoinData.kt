package com.coursework.data.responses.sub

import com.google.gson.annotations.SerializedName

data class CoinData(
    @SerializedName("id") val id: String?,
    @SerializedName("symbol") val symbol: String?, // Аббревиатура криптовалюты, которую обычно используют для ее отображения и торговли
    @SerializedName("name") val name: String?, // Название криптовалюты
    @SerializedName("nameid") val nameId: String?, // Часто используемое имя криптовалюты в формате URL или другом стандартном формате
    @SerializedName("rank") val rank: Int, // Позиция криптовалюты в рейтинге по рыночной капитализации.
    @SerializedName("price_usd") val priceUsd: String?, // Текущая цена криптовалюты в долларах США
    @SerializedName("percent_change_24h") val percentChange24h: String?, // Процентная изменение цены криптовалюты за последние 24 часа
    @SerializedName("percent_change_1h") val percentChange1h: String?, //  Процентная изменение цены криптовалюты за последний час
    @SerializedName("percent_change_7d") val percentChange7d: String?, // Процентная изменение цены криптовалюты за последние 7 дней
    @SerializedName("price_btc") val priceBtc: String?, // Текущая цена криптовалюты в биткоинах
    @SerializedName("market_cap_usd") val marketCapUsd: String?, // Рыночная капитализация криптовалюты в долларах США
    @SerializedName("volume24") val volume24: Double, // Объем торгов в течении последних 24 часов
    @SerializedName("volume24a") val volume24a: Double, // Аналогично volume24, но значение указано в другой единице измерения
    @SerializedName("csupply") val csupply: String?, // Общее количество выпущенных монет или токенов криптовалюты
    @SerializedName("tsupply") val tsupply: String?, // Общее количество токенов, которые могут быть выпущены в течении времени
    @SerializedName("msupply") val msupply: String?, // Общее количество токенов, которые могут быть выпущены в будущем
) {

    override fun toString(): String {
        return """
        ID: $id
        Symbol: $symbol
        Name: $name
        Name ID: $nameId
        Rank: $rank
        Price: $priceUsd USD / $priceBtc BTC
        Market capital.: $marketCapUsd USD
        1-hour change: $percentChange1h %
        24-hours change: $percentChange24h %
        7-days change: $percentChange7d %
        Bargaining volume 24: $volume24
        Bargaining volume 24 alt: $volume24a
        Supplied: $csupply
        Supplying: $tsupply
        Will be supplied: $msupply
        """.trimIndent()
    }
}