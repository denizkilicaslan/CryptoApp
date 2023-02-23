package com.denizzz.retrofitkt.service

import com.denizzz.retrofitkt.model.CryptoModel
//import io.reactivex.Observer
import retrofit2.Call
import retrofit2.http.GET
import android.database.Observable
import io.reactivex.Observer


interface CryptoAPI {

    //GET POST UPDATE DELETE
    //https://raw.githubusercontent.com  (BaseURL)
    // /atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("/atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Observer<List<CryptoModel>>
    //fun getData(): Call<List<CryptoModel>>
}