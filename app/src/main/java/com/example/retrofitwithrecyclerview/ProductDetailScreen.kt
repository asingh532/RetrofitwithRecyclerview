package com.example.retrofitwithrecyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitwithrecyclerview.model.ProductModel
import kotlinx.android.synthetic.main.activity_product_detail_screen.*

class ProductDetailScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail_screen)

        setSupportActionBar(toolbarDetail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Product Detail Screen"
        }

        val model = intent.getSerializableExtra("intent") as ProductModel

        aboutProductView.text = model.description

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}