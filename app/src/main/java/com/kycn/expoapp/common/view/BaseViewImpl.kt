package com.kycn.expoapp.common.view

import android.content.Context
import android.view.View
import androidx.annotation.IdRes

open class BaseViewImpl : BaseView {
    private lateinit var root : View

    override fun getRoot(): View = root

    fun setRoot(rootView: View) {
        root = rootView
    }

    fun <T : View?> findViewById(@IdRes id: Int): T{
        return root.findViewById<View>(id) as T
    }

    fun getContext(): Context? {
        return root.context
    }
}