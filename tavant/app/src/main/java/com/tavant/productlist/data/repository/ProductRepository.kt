package com.tavant.productlist.data.repository

import com.tavant.productlist.data.network.ProductAPI


class ProductRepository(private val api : ProductAPI) : SafeAPiRequest() {

    suspend fun getProducts(limit : Int) = apiRequest { api.getProducts(limit) }
}