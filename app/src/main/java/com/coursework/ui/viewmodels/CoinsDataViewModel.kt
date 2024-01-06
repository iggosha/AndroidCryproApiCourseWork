package com.coursework.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.coursework.data.controller.RetrofitController
import com.coursework.data.responses.sub.CoinData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinsDataViewModel(private val retrofitController: RetrofitController) : ViewModel() {

    private var _coinsDataList = MutableLiveData<List<CoinData>>()
    val coinsDataList: LiveData<List<CoinData>> = _coinsDataList
    private var _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> = _progressBarVisibility

    init {
        refreshCoinsData()
    }

    fun refreshCoinsData() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarVisibility.postValue(true)
            _coinsDataList.postValue(retrofitController.getTickers().coins)
            _progressBarVisibility.postValue(false)
        }
    }

    companion object {

        fun Factory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras
            ): T {
                return CoinsDataViewModel(
                    RetrofitController.getInstance()
                ) as T
            }
        }
    }
}