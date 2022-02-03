package com.example.retrofitwithrecyclerview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitwithrecyclerview.adapter.ProductAdapter
import com.example.retrofitwithrecyclerview.model.ProductModel
import com.example.retrofitwithrecyclerview.network.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ProductAdapter.myClickEvent {

    private lateinit var productList: MutableList<ProductModel>
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = View.VISIBLE
        initView()
        getAllProduct()
    }

    private fun initView() {
        productListRecyclerView.layoutManager = LinearLayoutManager(this)
        productListRecyclerView.setHasFixedSize(true)
        productList = mutableListOf<ProductModel>()
        productAdapter = ProductAdapter(productList,this)
        productListRecyclerView.adapter = productAdapter

    }

    private fun getAllProduct() {
        val call = ApiService.apiProvider.getAllProducts()
        call.enqueue(object : Callback<List<ProductModel>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {
                if (response.isSuccessful) {
                    progressBar.visibility = View.INVISIBLE
                    val list: List<ProductModel>? = response.body()
                    productList.addAll(list!!)
                    productAdapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                progressBar.visibility = View.INVISIBLE
                Log.e("HomeScreen", "onFailure: ${t.localizedMessage}")
            }

        })
    }

    override fun onClickTrigger(position: Int) {
        val productModel:ProductModel = productList[position]


        val intent:Intent = Intent(this,ProductDetailScreen::class.java)
        intent.putExtra("intent",productModel)
        startActivity(intent)
    }


}