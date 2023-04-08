package com.tavant.productlist.ui.products

import com.tavant.productlist.data.model.ProductItem

interface RecyclerViewClickListener{
    fun onRecyclerViewItemClick(product : ProductItem)
}