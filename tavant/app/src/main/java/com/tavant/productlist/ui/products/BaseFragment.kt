package com.tavant.productlist.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>
    (private val bindingInflater : (inflater : LayoutInflater) -> VB) : Fragment() {

    private var _binding : VB? = null

    val binding : VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_base, container, false)
        _binding = bindingInflater.invoke(inflater)
        if(_binding == null)
            throw IllegalArgumentException("Binding can't null")
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}