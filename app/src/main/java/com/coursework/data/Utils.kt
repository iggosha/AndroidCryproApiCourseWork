package com.coursework.data

interface NavToMarketsInterface {

    fun goToMarkets(coinId: String)
}

interface NavToExchangeInterface {

    fun goToExchange(exchangeName: String)
}
interface NavToCoinInterface {

    fun goToCoin(coinId: String)
}