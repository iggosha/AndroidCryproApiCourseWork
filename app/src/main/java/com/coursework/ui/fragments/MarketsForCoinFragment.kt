package com.coursework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.coursework.data.NavToExchangeInterface
import com.coursework.databinding.FragmentMarketsForCoinBinding
import com.coursework.ui.adapters.MarketsForCoinAdapter
import com.coursework.ui.viewmodels.MarketsForCoinViewModel

class MarketsForCoinFragment : Fragment() {

    private var _binding: FragmentMarketsForCoinBinding? = null
    private val binding get() = _binding!!
    private lateinit var marketsForCoinAdapter: MarketsForCoinAdapter
    private val args by navArgs<MarketsForCoinFragmentArgs>()
    private val coinId by lazy { args.coinId }
    private val viewModel: MarketsForCoinViewModel by viewModels {
        MarketsForCoinViewModel.Factory(coinId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketsForCoinBinding.inflate(layoutInflater, container, false)

        binding.marketsForCoinRecycler.layoutManager = LinearLayoutManager(context)
        marketsForCoinAdapter = MarketsForCoinAdapter(action).apply {
            viewModel.marketsForCoinList.observe(viewLifecycleOwner) {
                marketsForCoinList = it
            }
        }
        binding.marketsForCoinRecycler.adapter = marketsForCoinAdapter

        binding.refreshButton.setOnClickListener {
            viewModel.refreshMarkets()
        }
        viewModel.progressBarVisibility.observe(viewLifecycleOwner) {
            if (it) {
                binding.spinnerRing.visibility = ProgressBar.VISIBLE
            } else {
                binding.spinnerRing.visibility = ProgressBar.GONE
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val action = object : NavToExchangeInterface {
        override fun goToExchange(exchangeName: String) {
            val action =
                MarketsForCoinFragmentDirections.actionMarketsForCoinFragmentToExchangeFragment(
                    exchangeName
                )
            findNavController().navigate(action)
        }
    }
}