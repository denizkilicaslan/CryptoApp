package com.denizzz.retrofitkt.model

import com.google.gson.annotations.SerializedName


data class CryptoModel ( //aynı isimde old için gerek yok @SerializedName
//   @SerializedName("currency")
    val currency: String,

   //@SerializedName("price")
    val price:String )