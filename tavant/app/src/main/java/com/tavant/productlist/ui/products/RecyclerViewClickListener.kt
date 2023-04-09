package com.tavant.productlist.ui.products

import android.view.View
import com.tavant.productlist.data.model.ProductItem

interface RecyclerViewClickListener{
    fun onRecyclerViewItemClick(view: View, product: ProductItem)
}