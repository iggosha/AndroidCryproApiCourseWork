package com.coursework.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.coursework.data.controller.RetrofitController
import com.coursework.data.responses.SocialStatsResponse
import com.coursework.data.responses.CoinData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinExtendedDataViewModel(
    private val coinId: String,
    private val retrofitController: RetrofitController
) : ViewModel() {

    private var _coinData = MutableLiveData<CoinData>()
    val coinData: LiveData<CoinData> = _coinData
    private var _coinSocialStats = MutableLiveData<SocialStatsResponse>()
    val coinSocialStats: LiveData<SocialStatsResponse> = _coinSocialStats
    private var _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> = _progressBarVisibility

    init {
        refreshCoinData()
    }

    fun refreshCoinData() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarVisibility.postValue(true)
            _coinData.postValue(retrofitController.getTickerById(coinId)[0])
            _coinSocialStats.postValue(retrofitController.getSocialStats(coinId))
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
                    return CoinExtendedDataViewModel(
                        coinId, RetrofitController.getInstance()
                    ) as T
                }
            }
    }
}