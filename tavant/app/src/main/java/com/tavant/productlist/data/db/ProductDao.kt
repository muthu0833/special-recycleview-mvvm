package com.tavant.productlist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tavant.productlist.data.model.ProductItem

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductItem>)

    @Query("SELECT * FROM products")
    fun getProducts() : LiveData<List<ProductItem>>

    @Query("SELECT EXISTS(SELECT * FROM products)")
    fun isExists(): Boolean

    @Update
    suspend fun updateProduct(productItem: ProductItem)
}