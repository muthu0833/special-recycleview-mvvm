package com.tavant.productlist.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.tavant.productlist.utils.Utils
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context : Context) : Interceptor {
    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!Utils.isInternetAvailable(applicationContext))
            throw NoInternetException("Make sure the Internet Connectivity")
        return chain.proceed(chain.request())
    }
}