package com.coursework.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coursework.R
import com.coursework.data.responses.sub.CoinData

class CoinRecyclerAdapter : RecyclerView.Adapter<CoinRecyclerAdapter.CardHolder>() {

    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val coinSymbol: TextView = itemView.findViewById(R.id.coinSymbol)
        val coinName: TextView = itemView.findViewById(R.id.coinName)
        val coinPriceUsd: TextView = itemView.findViewById(R.id.coinPriceUsd)
    }

    var coinDataList: List<CoinData> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.coin_item, parent, false)
        return CardHolder(itemView)
    }

    override fun getItemCount() = coinDataList.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val coinDataItem = coinDataList[position]
        holder.coinSymbol.text = coinDataItem.symbol
        holder.coinName.text = coinDataItem.name
        holder.coinPriceUsd.text =
            "${(kotlin.math.round(coinDataItem.priceUsd!!.toDouble() * 1000) / 1000)}$"
        holder.itemView.tag = coinDataItem.id
        holder.coinSymbol.setOnClickListener {
            AlertDialog.Builder(holder.coinSymbol.context)
                .setIcon(R.drawable.question_icon).setTitle("Coin data:")
                .setMessage(coinDataItem.toString()).setNegativeButton("Close") { _, _ -> }.show()
        }
    }
}