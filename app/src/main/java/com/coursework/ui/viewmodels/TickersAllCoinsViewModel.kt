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

class TickersAllCoinsViewModel(private val retrofitController: RetrofitController) : ViewModel() {

    private var _coinList = MutableLiveData<List<CoinData>>()
    val coinList: LiveData<List<CoinData>> = _coinList
    private var _isVisible = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> = _isVisible

    init {
        refreshCoins()
    }

    fun refreshCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            _isVisible.postValue(true)
            _coinList.postValue(retrofitController.getTickers().coins)
            _isVisible.postValue(false)
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras
            ): T {
                return TickersAllCoinsViewModel(
                    RetrofitController.getInstance()
                ) as T
            }
        }
    }
}