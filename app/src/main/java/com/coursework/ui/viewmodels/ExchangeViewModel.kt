package com.coursework.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.coursework.data.controller.RetrofitController
import com.coursework.data.responses.sub.CurrencyPair
import com.coursework.data.responses.sub.ExchangeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExchangeViewModel(
    private val exchangeName: String, private val retrofitController: RetrofitController
) : ViewModel() {

    private var _exchange = MutableLiveData<ExchangeData>()
    val exchange: LiveData<ExchangeData> = _exchange
    private var _currencyPairsList = MutableLiveData<List<CurrencyPair>>()
    val currencyPairsList: LiveData<List<CurrencyPair>> = _currencyPairsList
    private var _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> = _progressBarVisibility

    init {
        refreshExchange()
    }

    fun refreshExchange() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarVisibility.postValue(true)
            val exchange = findExchangeByName(exchangeName)
            _exchange.postValue(exchange)
            val currencyPairs = getCurrencyPairsByExchangeId(exchange.id)
            _currencyPairsList.postValue(currencyPairs)
            _progressBarVisibility.postValue(false)
        }
    }

    private suspend fun findExchangeByName(exchangeName: String): ExchangeData =
        withContext(Dispatchers.IO) {
            retrofitController.getExchanges().values.find { it.name == exchangeName }!!
        }

    private suspend fun getCurrencyPairsByExchangeId(exchangeId: String): List<CurrencyPair> =
        withContext(Dispatchers.IO) {
            retrofitController.getExchangeDataById(exchangeId).currencyPairs
        }

    companion object {

        fun Factory(exchangeName: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>, extras: CreationExtras
                ): T {
                    return ExchangeViewModel(
                        exchangeName, RetrofitController.getInstance()
                    ) as T
                }
            }
    }
}