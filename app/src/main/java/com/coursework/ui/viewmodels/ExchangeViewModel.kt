package com.coursework.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.coursework.data.controller.RetrofitController
import com.coursework.data.responses.sub.ExchangeData
import kotlinx.coroutines.launch

class ExchangeViewModel(
    private val exchangeName: String,
    private val retrofitController: RetrofitController
) : ViewModel() {

    private var _exchanges = MutableLiveData<ExchangeData>()
    val exchanges: LiveData<ExchangeData> = _exchanges
    private var _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> = _progressBarVisibility

    init {
        refreshExchange()
    }

    fun refreshExchange() {
        viewModelScope.launch {
            _progressBarVisibility.postValue(true)
            _exchanges.postValue(findExchangeByName(exchangeName))
            _progressBarVisibility.postValue(false)
        }
    }

    private suspend fun findExchangeByName(exchangeName: String): ExchangeData? =
        retrofitController
            .getExchanges()
            .values
            .find { it.name == exchangeName }

    companion object {

        fun Factory(exchangeName: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>, extras: CreationExtras
                ): T {
                    return ExchangeViewModel(
                        exchangeName,
                        RetrofitController.getInstance()
                    ) as T
                }
            }
    }
}