package com.tavant.productlist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tavant.productlist.data.model.ProductItem

@Database(entities = [ProductItem::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao() : ProductDao

    companion object{

        @Volatile
        private var INSTANCE : ProductDatabase ?= null

        fun getDatabase(context : Context) : ProductDatabase {
            if(INSTANCE == null){
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): ProductDatabase {
            return Room.databaseBuilder(context.applicationContext, ProductDatabase::class.java,
            "product_database").build()
        }

    }
}