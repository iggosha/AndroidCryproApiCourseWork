package com.coursework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.coursework.data.controller.RetrofitController
import com.coursework.databinding.FragmentGlobalCryptoDataBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GlobalCryptoDataFragment : Fragment() {

    private var _binding: FragmentGlobalCryptoDataBinding? = null
    private val binding get() = _binding!!
    private val retrofitController = RetrofitController.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGlobalCryptoDataBinding.inflate(layoutInflater, container, false)

        binding.fragButton1.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                binding.spinnerRing.visibility = ProgressBar.VISIBLE
                var data: String
                withContext(Dispatchers.IO) {
                    data = retrofitController.getGlobalCryptoData().toString()
                }
                binding.textView.text = data
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
