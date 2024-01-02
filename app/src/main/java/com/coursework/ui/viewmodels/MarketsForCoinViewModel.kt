package com.coursework.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.coursework.data.controller.RetrofitController
import com.coursework.data.responses.MarketForCoinResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarketsForCoinViewModel(
    private val coinId: String,
    private val retrofitController: RetrofitController
) : ViewModel() {

    private var _marketsList = MutableLiveData<List<MarketForCoinResponse>>()
    val marketsList: LiveData<List<MarketForCoinResponse>> = _marketsList
    private var _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> = _progressBarVisibility

    init {
        refreshMarkets()
    }

    fun refreshMarkets() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarVisibility.postValue(true)
            val marketsForCoin = retrofitController.getMarketsForCoinById(coinId)
            _marketsList.postValue(marketsForCoin)
            _progressBarVisibility.postValue(false)
        }
    }

    companion object {

        fun Factory(coinId: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>, extras: CreationExtras
                ): T {
                    return MarketsForCoinViewModel(
                        coinId,
                        RetrofitController.getInstance()
                    ) as T
                }
            }
    }
}