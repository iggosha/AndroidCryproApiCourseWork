package com.coursework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.coursework.data.CoinsCodes.BITCOIN
import com.coursework.data.controller.RetrofitController
import com.coursework.databinding.FragmentMarketsForCoinBinding
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MarketsForCoinFragment : Fragment() {

    private var _binding: FragmentMarketsForCoinBinding? = null
    private val binding get() = _binding!!

    private val retrofitController = RetrofitController.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketsForCoinBinding.inflate(layoutInflater, container, false)


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
        binding.textView.text = retrofitController.getMarketsForCoinId(BITCOIN.value).toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}