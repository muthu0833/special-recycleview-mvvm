package com.tavant.productlist.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.net.URL

@BindingAdapter("image")
fun loadImage(view : ImageView, url: String){
 Glide.with(view).load(url).into(view)
}