package com.kycn.expoapp.common.view

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GlideImageLoader(private val mActivity: Activity) {
    private val requestOptions: RequestOptions = RequestOptions().centerCrop()

    fun loadUrl(url: String, imageView: ImageView) {
        Glide.with(mActivity).load(url).apply(requestOptions).into(imageView)
    }
}