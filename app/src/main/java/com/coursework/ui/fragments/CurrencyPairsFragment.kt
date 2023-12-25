package com.coursework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.coursework.data.ExchangesCodes.BINANCE
import com.coursework.data.controller.RetrofitController
import com.coursework.databinding.FragmentCurrencyPairsBinding
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class CurrencyPairsFragment : Fragment() {

    private var _binding: FragmentCurrencyPairsBinding? = null
    private val binding get() = _binding!!
    private val retrofitController = RetrofitController.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyPairsBinding.inflate(layoutInflater, container, false)


        binding.fragButton1.setOnClickListener {
            thread {
                lifecycleScope.launch {
                    loadData()
                }
            }
        }

        return binding.root
    }

    @WorkerThread
    suspend fun loadData() {
        binding.textView.text =
            retrofitController.getExchangeDataById(BINANCE.value).currencyPairs.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

