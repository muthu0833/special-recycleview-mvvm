package com.tavant.productlist.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tavant.productlist.R
import com.tavant.productlist.data.model.ProductItem
import com.tavant.productlist.databinding.RecyclerviewItemBinding

class ProductAdapter(private val products : List<ProductItem>,
    private val listener: RecyclerViewClickListener) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    class ProductViewHolder(val recyclerviewItemBinding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(recyclerviewItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recyclerview_item, parent,false))


    override fun getItemCount(): Int {
       return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.recyclerviewItemBinding.product = products[position]
        holder.recyclerviewItemBinding.root.setOnClickListener{
            listener.onRecyclerViewItemClick(products[position])
        }

    }
}