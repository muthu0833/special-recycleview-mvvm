package com.tavant.productlist.ui.products

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tavant.productlist.data.model.ProductItem
import com.tavant.productlist.databinding.FragmentProductDetailBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>
    (FragmentProductDetailBinding::inflate), KodeinAware {

    override val kodein by kodein()
    private lateinit var viewModel: ProductViewModel
    private val factory : ProductViewModelFactory by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(requireActivity(), factory).get(ProductViewModel::class.java)
        viewModel.selectedProduct.observe(viewLifecycleOwner, Observer { product ->
            println("prodcuts ${product.title}")
            binding.product = product
            binding.lifecycleOwner = this
        })
    }
}