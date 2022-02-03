package com.example.retrofitwithrecyclerview.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitwithrecyclerview.R
import com.example.retrofitwithrecyclerview.model.ProductModel
import kotlinx.android.synthetic.main.product_item_adapter.view.*

class ProductAdapter(private val productList:List<ProductModel>,
private val listener:myClickEvent) :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private lateinit var mContext: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        mContext = parent.context
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.product_item_adapter,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productTitle.text = productList[position].title
        holder.productPrice.text = productList[position].price.toString()
        holder.productCategory.text = productList[position].category
        holder.productRating.rating = productList[position].rating.rate.toFloat()

        Glide.with(mContext)
            .load(Uri.parse(productList[position].image))
            .into(holder.productImage)

    }

    override fun getItemCount()=productList.size


     inner class ProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
         View.OnClickListener {
        val productImage: ImageView = itemView.imageView
         val productTitle :TextView = itemView.titleView
         val productPrice:TextView = itemView.priceView
         val productCategory:TextView = itemView.categoryView
         val productRating:RatingBar = itemView.ratingBar

         init {
             itemView.setOnClickListener(this)
         }

         override fun onClick(v: View?) {
             val position = adapterPosition
                listener.onClickTrigger(position)
         }

     }
    interface myClickEvent{
        fun onClickTrigger(position: Int)
    }
}