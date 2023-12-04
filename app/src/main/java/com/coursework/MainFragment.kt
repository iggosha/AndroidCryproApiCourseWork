package com.coursework

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import com.coursework.databinding.FragmentMainBinding
import com.coursework.responses.TickersAllCoinsResponse
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.concurrent.thread

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        binding.saveButton.setOnClickListener {
            thread {
                getCoinData()
            }
        }
        return binding.root
    }

    @WorkerThread
    fun getCoinData() {
        val reqUrl = "https://api.coinlore.net/api/tickers/"
        val coinData = OkHttpClient()
        val request = Request.Builder().url(reqUrl).build()
        coinData.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        Log.e("HttpError", "Not loaded or not successful")
                    } else {
                        val jsonResponse = response.body!!.string()
                        val response = Json.decodeFromString<TickersAllCoinsResponse>(jsonResponse)
                        Handler(Looper.getMainLooper()).post {
                            binding.asd.text = response.toString()
                        }
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }
}