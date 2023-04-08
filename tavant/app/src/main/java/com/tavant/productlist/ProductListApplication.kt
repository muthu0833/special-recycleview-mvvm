package com.tavant.productlist

import android.app.Application
import com.tavant.productlist.data.network.NetworkConnectionInterceptor
import com.tavant.productlist.data.network.ProductAPI
import com.tavant.productlist.data.repository.ProductRepository
import com.tavant.productlist.ui.products.ProductViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ProductListApplication : Application(), KodeinAware {
    override val kodein =  Kodein.lazy {
        import(androidXModule(this@ProductListApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ProductAPI(instance()) }
        bind() from singleton { ProductRepository(instance()) }
        bind() from provider  { ProductViewModelFactory(instance())}
    }
}