package com.tavant.productlist.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tavant.productlist.utils.Coroutines
import com.tavant.productlist.data.model.ProductItem
import com.tavant.productlist.data.repository.ProductRepository
import kotlinx.coroutines.Job

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private lateinit var job : Job

    private var _product = MutableLiveData<List<ProductItem>>()

    val product : LiveData<List<ProductItem>>
        get() = _product

    private var _selectedProduct = MutableLiveData<ProductItem>()

    val selectedProduct : LiveData<ProductItem>
        get() = _selectedProduct

    fun getProducts(){
      job =  Coroutines.ioThenMain(
            {repository.getProducts(10)},
            {saveProducts(it)})

    }
    fun isExists() = repository.isExists()

    fun updateProduct(productItem: ProductItem){
        job =  Coroutines.ioThenMain(
            {repository.updateProduct(productItem)},
            {})
    }

    fun getCachedProducts() = repository.getProducts()

    fun saveProducts(products: List<ProductItem>) //= repository.saveProducts(products)
    {
        job =  Coroutines.ioThenMain(
            {repository.saveProducts(products)},
            {})
    }
    fun selectedProduct(selectedProduct : ProductItem){
        _selectedProduct.value = selectedProduct
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized){
            job.cancel()
        }
    }
}