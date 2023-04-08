package com.tavant.productlist.data.network

import com.tavant.productlist.data.model.ProductItem
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductAPI {
//    https://fakestoreapi.com/products?limit=10

    @GET("/products")
    suspend fun getProducts(@Query("limit")  limit : Int) : Response<ArrayList<ProductItem>>

    companion object{
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : ProductAPI {

            val okHttpClient = OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor).build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://fakestoreapi.com/")
                .build()
                .create(ProductAPI::class.java)

        }
    }
}