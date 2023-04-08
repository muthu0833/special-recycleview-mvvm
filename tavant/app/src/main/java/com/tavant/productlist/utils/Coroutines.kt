package com.tavant.productlist.utils

import kotlinx.coroutines.*

object Coroutines {

    fun<T : Any> ioThenMain(work : suspend (() -> T?), callback : ((T) -> Unit)) =
            CoroutineScope(Dispatchers.Main).launch {
                val data = CoroutineScope(Dispatchers.IO).async {
                    return@async work()
                }.await()
                data?.let { callback(it) }

    }
}