package com.tavant.productlist.data.repository

import com.tavant.productlist.data.db.ProductDatabase
import com.tavant.productlist.data.model.ProductItem
import com.tavant.productlist.data.network.ProductAPI


class ProductRepository(private val api : ProductAPI, private val db : ProductDatabase) : SafeAPiRequest() {

    suspend fun getProducts(limit : Int) = apiRequest { api.getProducts(limit) }

    fun getProducts() = db.getProductDao().getProducts()

    fun isExists():Boolean  {
        return db.getProductDao().isExists()
    }

    suspend fun updateProduct(productItem: ProductItem) = db.getProductDao().updateProduct(productItem)

    suspend fun saveProducts(products : List<ProductItem>) = db.getProductDao().insertProducts(products)
}