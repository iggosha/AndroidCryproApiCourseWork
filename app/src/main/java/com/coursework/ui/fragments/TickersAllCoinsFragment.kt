package com.coursework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coursework.databinding.FragmentTickersForAllCoinsBinding
import com.coursework.ui.CoinRecyclerAdapter
import com.coursework.ui.viewmodels.TickersAllCoinsViewModel

class TickersAllCoinsFragment : Fragment() {

    private var _binding: FragmentTickersForAllCoinsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CoinRecyclerAdapter
    private val viewModel: TickersAllCoinsViewModel by viewModels { TickersAllCoinsViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTickersForAllCoinsBinding.inflate(layoutInflater, container, false)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CoinRecyclerAdapter().apply {
            viewModel.coinList.observe(viewLifecycleOwner) {
                coinDataList = it
            }
        }
        recyclerView.adapter = adapter

        binding.refreshButton.setOnClickListener {
            viewModel.refreshCoins()
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