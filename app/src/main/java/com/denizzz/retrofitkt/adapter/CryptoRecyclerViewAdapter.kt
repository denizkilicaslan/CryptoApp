package com.denizzz.retrofitkt.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denizzz.retrofitkt.R
import com.denizzz.retrofitkt.model.CryptoModel

class CryptoRecyclerViewAdapter(private val cryptoList:ArrayList<CryptoModel>,private val listener:Listener): RecyclerView.Adapter<CryptoRecyclerViewAdapter.MyViewHolder>() {

     //val private colors:Array ={"#30695a","#c98466","#465e7d","#69439d",
       // "#9aceeb","#669962","#006027","#814545"}

    interface Listener {
        fun onItemClick(cryptoModel: CryptoModel)
    }

    val colors:Array<String> = arrayOf("#30695a","#c98466","#465e7d","#69439d",
         "#9aceeb","#669962","#006027","#814545")

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       var  text_name:TextView=itemView.findViewById(R.id.text_name)
       var text_price:TextView=itemView.findViewById(R.id.text_price)

        fun bind(cryptoModel: CryptoModel,colors:Array<String>,position: Int,listener: Listener){
            itemView.setOnClickListener(){
                listener.onItemClick(cryptoModel)
            }
            text_name.setBackgroundColor(Color.parseColor(colors[position%8]))
            text_name.text=cryptoModel.currency
            text_price.text=cryptoModel.price
            text_price.setBackgroundColor(Color.parseColor(colors[position%8]))
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return MyViewHolder(view)


    }

    // hangi item ne gosterıcek verısı yazılan yer
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(cryptoList[position], colors,position, listener)
    }

    override fun getItemCount(): Int {
       return cryptoList.size
    }
}