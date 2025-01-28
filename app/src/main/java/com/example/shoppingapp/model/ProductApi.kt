package com.example.shoppingapp.model

import com.example.shoppingapp.model.data.MyData
import com.example.shoppingapp.model.data.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("/products")
    suspend fun getData() : Response<MyData>


}