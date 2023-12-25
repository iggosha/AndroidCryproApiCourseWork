package com.coursework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.coursework.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)


        binding.fragButton1.setOnClickListener {
            val action =
                StartFragmentDirections.actionStartFragmentToCurrencyPairsFragment()
            findNavController().navigate(action)
        }
        binding.fragButton2.setOnClickListener {
            val action =
                StartFragmentDirections.actionStartFragmentToGlobalCryptoDataFragment()
            findNavController().navigate(action)
        }
        binding.fragButton3.setOnClickListener {
            val action =
                StartFragmentDirections.actionStartFragmentToMarketsForCoinFragment()
            findNavController().navigate(action)
        }
        binding.fragButton4.setOnClickListener {
            val action =
                StartFragmentDirections.actionStartFragmentToTickersAllCoinsFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


// ресайклер
// фэктори
// вьюмоделы
// 