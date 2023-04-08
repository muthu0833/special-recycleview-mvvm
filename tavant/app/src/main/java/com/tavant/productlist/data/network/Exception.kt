package com.tavant.productlist.data.network

import java.io.IOException

class APiException(message : String) : IOException(message)
class NoInternetException(message: String) :IOException(message)