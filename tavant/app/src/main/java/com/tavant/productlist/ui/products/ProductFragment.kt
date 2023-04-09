package com.tavant.productlist.ui.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tavant.productlist.R
import com.tavant.productlist.data.model.ProductItem
import com.tavant.productlist.databinding.FragmentProductBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProductFragment : BaseFragment<FragmentProductBinding>
    (FragmentProductBinding::inflate), RecyclerViewClickListener, KodeinAware {

    override val kodein by kodein()
    private lateinit var viewModel: ProductViewModel
    private val factory : ProductViewModelFactory by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            viewModel = ViewModelProvider(requireActivity(), factory).get(ProductViewModel::class.java)
            lifecycleScope.launch(Dispatchers.IO) {
                if(!viewModel.isExists())
                    viewModel.getProducts()
            }
            flag = false
                viewModel.getCachedProducts().observe(viewLifecycleOwner, Observer { products ->
                    bindUI(products)
                })

//            viewModel.product.observe(viewLifecycleOwner, Observer { products ->
//                bindUI(products)
//            })
        }catch(e : Exception){
            Log.e(FILENAME, "exception $e")
        }

    }


    private fun bindUI(products : List<ProductItem>) {
        if(!flag)
        binding.recyclerViewProduct.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = ProductAdapter(products, this)
        }
    }
    var flag : Boolean = false
    override fun onRecyclerViewItemClick(view: View, product: ProductItem) {
        when(view.id){
            R.id.productFavourite -> {
                flag = true
                product.favourite = (view as CheckBox).isChecked
                viewModel.updateProduct(product)
            }else -> {
                viewModel.selectedProduct(product)
                findNavController().navigate(R.id.action_productFragment_to_productDetailFragment)
            }
        }

//        Toast.makeText(requireContext(), "product : $product", Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val FILENAME = "ProductFragment"
    }

}