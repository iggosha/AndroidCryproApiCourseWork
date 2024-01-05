package com.coursework.ui.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coursework.R
import com.coursework.data.NavToExchangeInterface
import com.coursework.data.responses.MarketForCoinResponse

class MarketsForCoinAdapter(private val navToExchangeAction: NavToExchangeInterface) :
    RecyclerView.Adapter<MarketsForCoinAdapter.MarketHolder>() {

    class MarketHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val marketName: TextView = itemView.findViewById(R.id.marketName)
        val marketCoinPriceUsd: TextView = itemView.findViewById(R.id.marketCoinPriceUsd)
        val marketCoinPriceQuote: TextView = itemView.findViewById(R.id.marketCoinPriceQuote)
    }

    var marketForCoinList: List<MarketForCoinResponse> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.market_for_coin_item, parent, false)
        return MarketHolder(itemView)
    }

    override fun getItemCount() = marketForCoinList.size

    override fun onBindViewHolder(holder: MarketHolder, position: Int) {
        val marketItem = marketForCoinList[position]
        holder.itemView.tag = marketItem.name
        holder.marketName.text = marketItem.name
        holder.marketCoinPriceUsd.text =
            "${marketItem.base} ${kotlin.math.round(marketItem.priceUsd * 1000) / 1000}$"
        holder.marketCoinPriceQuote.text =
            "${marketItem.base} ${kotlin.math.round(marketItem.price * 1000) / 1000} ${marketItem.quote}"
        holder.marketName.setOnClickListener {
            AlertDialog.Builder(holder.marketName.context)
                .setIcon(R.drawable.question_icon).setTitle("Market data:")
                .setMessage(marketItem.toString())
                .setNegativeButton("More") { _, _ ->
                    navToExchangeAction.goToExchange(marketItem.name)
                }
                .setPositiveButton("Close") { _, _ -> }.show()
        }
    }
}