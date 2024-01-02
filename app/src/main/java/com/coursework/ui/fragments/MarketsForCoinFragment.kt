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
import androidx.recyclerview.widget.RecyclerView
import com.coursework.databinding.FragmentMarketsForCoinBinding
import com.coursework.ui.MarketsForCoinAdapter
import com.coursework.ui.viewmodels.MarketsForCoinViewModel

class MarketsForCoinFragment : Fragment() {

    private var _binding: FragmentMarketsForCoinBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MarketsForCoinAdapter
    private val args by navArgs<MarketsForCoinFragmentArgs>()
    private val coinId by lazy { args.coinId }
    private val viewModel: MarketsForCoinViewModel by viewModels {
        MarketsForCoinViewModel.Factory(
            coinId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketsForCoinBinding.inflate(layoutInflater, container, false)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MarketsForCoinAdapter().apply {
            viewModel.marketsList.observe(viewLifecycleOwner) {
                marketForCoinList = it
            }
        }
        recyclerView.adapter = adapter

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
}