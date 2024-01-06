package com.coursework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.coursework.databinding.FragmentExchangeBinding
import com.coursework.ui.adapters.CurrencyPairsAdapter
import com.coursework.ui.viewmodels.ExchangeViewModel

class ExchangeFragment : Fragment() {

    private var _binding: FragmentExchangeBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ExchangeFragmentArgs>()
    private val exchangeName by lazy { args.exchangeName }
    private lateinit var currencyPairsAdapter: CurrencyPairsAdapter
    private val viewModel: ExchangeViewModel by viewModels { ExchangeViewModel.Factory(exchangeName) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExchangeBinding.inflate(layoutInflater, container, false)

        viewModel.exchange.observe(viewLifecycleOwner) {
            binding.exchangeData.text = it.toString()
        }

        binding.currencyPairsRecycler.layoutManager = LinearLayoutManager(context)
        currencyPairsAdapter = CurrencyPairsAdapter().apply {
            viewModel.currencyPairsList.observe(viewLifecycleOwner) {
                currencyPairList = it
            }
        }
        binding.currencyPairsRecycler.adapter = currencyPairsAdapter


        viewModel.progressBarVisibility.observe(viewLifecycleOwner) {
            if (it) {
                binding.spinnerRing.visibility = ProgressBar.VISIBLE
            } else {
                binding.spinnerRing.visibility = ProgressBar.GONE
            }
        }

        binding.refreshButton.setOnClickListener {
            viewModel.refreshExchange()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}