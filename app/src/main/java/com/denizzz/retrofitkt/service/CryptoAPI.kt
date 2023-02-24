package com.denizzz.retrofitkt.service

import com.denizzz.retrofitkt.model.CryptoModel
import io.reactivex.Observer
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    //GET POST UPDATE DELETE
    //https://raw.githubusercontent.com  (BaseURL)
    // /atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("/atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getData(): Response<List<CryptoModel>>
}