package com.coursework.ui.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coursework.R
import com.coursework.data.NavToCoinInterface
import com.coursework.data.NavToMarketsInterface
import com.coursework.data.responses.sub.CoinData

class CoinRecyclerAdapter(
    private val navToMarketsAction: NavToMarketsInterface,
    private val navToCoinInterface: NavToCoinInterface
) :
    RecyclerView.Adapter<CoinRecyclerAdapter.CoinDataHolder>() {

    class CoinDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val coinSymbol: TextView = itemView.findViewById(R.id.marketName)
        val coinName: TextView = itemView.findViewById(R.id.marketBase)
        val coinPriceUsd: TextView = itemView.findViewById(R.id.coinPriceUsd)
    }

    var coinsDataList: List<CoinData> = emptyList()
        @SuppressLint("NotifyDataSetChanged") set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinDataHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.coin_item, parent, false)
        return CoinDataHolder(itemView)
    }

    override fun getItemCount() = coinsDataList.size

    override fun onBindViewHolder(holder: CoinDataHolder, position: Int) {
        val coinDataItem = coinsDataList[position]
        holder.coinSymbol.text = coinDataItem.symbol
        holder.coinName.text = coinDataItem.name
        holder.coinPriceUsd.text =
            "${kotlin.math.round(coinDataItem.priceUsd!!.toDouble() * 1000) / 1000}$"
        holder.itemView.tag = coinDataItem.id
        holder.coinSymbol.setOnClickListener {
            AlertDialog.Builder(holder.coinSymbol.context).setIcon(R.drawable.question_icon)
                .setTitle("Coin data:").setMessage(coinDataItem.getShortData())
                .setNegativeButton("More") { _, _ ->
                    navToCoinInterface.goToCoin(coinDataItem.id!!)
                }
                .setPositiveButton("Close") { _, _ -> }.show()
        }
        holder.coinName.setOnClickListener {
            AlertDialog.Builder(holder.coinName.context).setIcon(R.drawable.question_icon)
                .setTitle("Coin data:").setMessage(coinDataItem.getShortData())
                .setNegativeButton("More") { _, _ ->
                    navToCoinInterface.goToCoin(coinDataItem.id!!)
                }
                .setPositiveButton("Close") { _, _ -> }.show()
        }
        holder.coinPriceUsd.setOnClickListener {
            navToMarketsAction.goToMarkets(coinDataItem.id!!)
        }
    }
}