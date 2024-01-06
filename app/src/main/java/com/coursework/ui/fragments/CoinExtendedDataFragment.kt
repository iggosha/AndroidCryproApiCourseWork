package com.coursework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.coursework.databinding.FragmentCoinExtendedDataBinding
import com.coursework.ui.viewmodels.CoinExtendedDataViewModel

class CoinExtendedDataFragment : Fragment() {

    private var _binding: FragmentCoinExtendedDataBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<CoinExtendedDataFragmentArgs>()
    private val coinId by lazy { args.coinId }
    private val viewModel: CoinExtendedDataViewModel by viewModels {
        CoinExtendedDataViewModel.Factory(coinId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinExtendedDataBinding.inflate(layoutInflater, container, false)

        viewModel.coinData.observe(viewLifecycleOwner) {
            binding.coinExtendedData.text = it.toString()
        }
        viewModel.coinSocialStats.observe(viewLifecycleOwner) {
            binding.coinSocialStats.text = it.toString()
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) {
            if (it) {
                binding.spinnerRing.visibility = ProgressBar.VISIBLE
            } else {
                binding.spinnerRing.visibility = ProgressBar.GONE
            }
        }

        binding.refreshButton.setOnClickListener {
            viewModel.refreshCoinData()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}