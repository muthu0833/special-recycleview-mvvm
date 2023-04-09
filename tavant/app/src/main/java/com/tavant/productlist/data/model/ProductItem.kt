package com.tavant.productlist.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductItem(
    val category: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String,
    val price: Double,
    @Embedded
    val rating: Rating,
    val title: String,
    var favourite : Boolean
)