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
import kotlinx.coroutines.withContext

class TickersAllCoinsViewModel(private val retrofitController: RetrofitController) : ViewModel() {

    private var _coinList = MutableLiveData<List<CoinData>>()
    var coinList: LiveData<List<CoinData>> = _coinList

    fun refreshCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _coinList.value = retrofitController.getTickers().coins
            }
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