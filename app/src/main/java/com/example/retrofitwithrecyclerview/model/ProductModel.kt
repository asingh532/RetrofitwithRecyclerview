package com.example.retrofitwithrecyclerview.model

import java.io.Serializable

data class ProductModel (
    var id:Int,
    var title:String,
    var price:Double,
    var description:String,
    var category:String,
    var image:String,
    var rating:Rating,
):Serializable
