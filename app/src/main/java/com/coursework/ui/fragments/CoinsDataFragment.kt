package com.coursework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.coursework.data.NavToCoinInterface
import com.coursework.data.NavToMarketsInterface
import com.coursework.databinding.FragmentCoinsDataBinding
import com.coursework.ui.adapters.CoinRecyclerAdapter
import com.coursework.ui.viewmodels.CoinsDataViewModel

class CoinsDataFragment : Fragment() {

    private var _binding: FragmentCoinsDataBinding? = null
    private val binding get() = _binding!!
    private lateinit var coinRecyclerAdapter: CoinRecyclerAdapter
    private val viewModel: CoinsDataViewModel by viewModels { CoinsDataViewModel.Factory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinsDataBinding.inflate(layoutInflater, container, false)

        binding.coinsDataRecycler.layoutManager = LinearLayoutManager(context)
        coinRecyclerAdapter = CoinRecyclerAdapter(actionToMarkets, actionToCoin).apply {
            viewModel.coinsDataList.observe(viewLifecycleOwner) {
                coinsDataList = it
            }
        }
        binding.coinsDataRecycler.adapter = coinRecyclerAdapter

        binding.refreshButton.setOnClickListener {
            viewModel.refreshCoinsData()
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

    private val actionToMarkets = object : NavToMarketsInterface {
        override fun goToMarkets(coinId: String) {
            val action =
                CoinsDataFragmentDirections.actionCoinsDataFragmentToMarketsForCoinFragment(coinId)
            findNavController().navigate(action)
        }
    }

    private val actionToCoin = object : NavToCoinInterface {
        override fun goToCoin(coinId: String) {
            val action =
                CoinsDataFragmentDirections.actionCoinsDataFragmentToCoinExtendedDataFragment(
                    coinId
                )
            findNavController().navigate(action)
        }
    }
}