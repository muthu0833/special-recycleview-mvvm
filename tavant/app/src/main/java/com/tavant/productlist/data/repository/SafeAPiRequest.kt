package com.tavant.productlist.data.repository

import com.tavant.productlist.data.network.APiException
import retrofit2.Response

abstract class SafeAPiRequest {

    suspend fun<T : Any> apiRequest(call : suspend () -> Response<T>) : T? {
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()
        }else{
            throw APiException(response.code().toString())
        }
    }
}