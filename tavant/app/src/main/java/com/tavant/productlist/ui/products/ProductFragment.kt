package com.tavant.productlist.ui.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tavant.productlist.R
import com.tavant.productlist.data.model.ProductItem
import com.tavant.productlist.databinding.FragmentProductBinding
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
            viewModel.getProducts()
            viewModel.product.observe(viewLifecycleOwner, Observer { products ->
                binding.recyclerViewProduct.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = ProductAdapter(products, this)
                }
            })
        }catch(e : Exception){
            Log.e(FILENAME, "exception $e")
        }

    }

    override fun onRecyclerViewItemClick(product: ProductItem) {
        viewModel.selectedProduct(product)
        findNavController().navigate(R.id.action_productFragment_to_productDetailFragment)
//        Toast.makeText(requireContext(), "product : $product", Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val FILENAME = "ProductFragment"
    }

}