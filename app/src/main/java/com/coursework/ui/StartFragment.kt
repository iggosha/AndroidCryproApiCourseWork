package com.coursework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.coursework.data.controller.RetrofitController
import com.coursework.databinding.FragmentStartBinding
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        binding.saveButton.setOnClickListener {
            thread {
                lifecycleScope.launch {
                    getCoinData()
                }
            }
        }
        return binding.root
    }

    @WorkerThread
    suspend fun getCoinData() {
        binding.asd.text = RetrofitController.getInstance().getTickers().toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}