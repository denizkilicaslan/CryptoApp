package com.denizzz.retrofitkt.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denizzz.retrofitkt.adapter.CryptoRecyclerViewAdapter
import com.denizzz.retrofitkt.databinding.ActivityMainBinding
import com.denizzz.retrofitkt.model.CryptoModel
import com.denizzz.retrofitkt.service.CryptoAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.Observable
//import io.reactivex.Observable
import android.database.Observable




class MainActivity : AppCompatActivity(),CryptoRecyclerViewAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: CryptoRecyclerViewAdapter

    private val BASE_URL="https://raw.githubusercontent.com"
    private lateinit var cryptoModels: ArrayList<CryptoModel>

    //Disposable
    private var compositeDisposable:CompositeDisposable?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable= CompositeDisposable()

        //RecyclerView
        val layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(this)
        binding.myRecyclerView.layoutManager=layoutManager


        //https://raw.githubusercontent.com/
        //atilsamancioglu/K21-JSONDataSet/master/crypto.json

        // base kısmı retrofıt ın ıcıne uzantılar kısmı ınterface yazılıyomus

        loadData()


    }

    fun loadData(){

        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .Subscribe(this::handleResponse))


    /*  -------------old vers-----------------------------


        //service apı ile retrofıt ı bırbırıne bagla
        val service=retrofit.create(CryptoAPI::class.java)


         val call=service.getData()
        call.enqueue(object :Callback<List<CryptoModel>>{

            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){

                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                        cryptoModels?.let {
                            recyclerViewAdapter= CryptoRecyclerViewAdapter(it,this@MainActivity)
                            binding.myRecyclerView.adapter=recyclerViewAdapter
                        }
/*
                        for (cryptoModel:CryptoModel in cryptoModels){
                            println(cryptoModel.price)
                            println(cryptoModel.currency)
                            println("***********************************")

                        }
  */                  }
                }
            }


            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })*/

    }

    private fun handleResponse(cryptoList : List<CryptoModel>){
        cryptoModels = ArrayList(cryptoList)

        cryptoModels?.let {
            recyclerViewAdapter=CryptoRecyclerViewAdapter(it,this@MainActivity)
            binding.myRecyclerView.adapter=recyclerViewAdapter

        }
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this@MainActivity,"${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }


    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable?.clear()
    }
}