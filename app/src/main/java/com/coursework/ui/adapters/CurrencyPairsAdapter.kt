package com.coursework.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coursework.R
import com.coursework.data.responses.sub.CurrencyPair

class CurrencyPairsAdapter : RecyclerView.Adapter<CurrencyPairsAdapter.CurrencyPairHolder>() {

    class CurrencyPairHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cpBase: TextView = itemView.findViewById(R.id.cpBase)
        val cpQuote: TextView = itemView.findViewById(R.id.cpQuote)
        val cpVolume: TextView = itemView.findViewById(R.id.cpVolume)
        val cpPrice: TextView = itemView.findViewById(R.id.cpPrice)
        val cpPriceUsd: TextView = itemView.findViewById(R.id.cpPriceUsd)
        val cpTime: TextView = itemView.findViewById(R.id.cpTime)
    }

    var currencyPairList: List<CurrencyPair> = emptyList()
        @SuppressLint("NotifyDataSetChanged") set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyPairHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.currency_pair_item, parent, false)
        return CurrencyPairHolder(itemView)
    }

    override fun getItemCount() = currencyPairList.size

    override fun onBindViewHolder(holder: CurrencyPairHolder, position: Int) {
        val currencyPairItem = currencyPairList[position]
        holder.cpBase.text = "Base: ${currencyPairItem.base}"
        holder.cpQuote.text = "Quote: ${currencyPairItem.quote}"
        holder.cpVolume.text = "Volume: ${currencyPairItem.volume}"
        holder.cpPrice.text = "Price: ${currencyPairItem.price}"
        holder.cpPriceUsd.text = "Price, $: ${currencyPairItem.priceUsd}"
        holder.cpTime.text = "Time: ${currencyPairItem.time}"
    }
}