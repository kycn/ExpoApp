package com.kycn.expoapp.common.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.kycn.expoapp.characters.view.CharactersView
import javax.inject.Inject
import kotlin.reflect.KClass

class ViewFactory @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: GlideImageLoader
) {

    fun <T : BaseView> newInstance(viewClass: KClass<T>, @Nullable container: ViewGroup?): T {
        val view: BaseView
        when (viewClass) {
            CharactersView::class -> {
                view = CharactersView(layoutInflater, imageLoader, container)
            }
            else -> {
                throw IllegalArgumentException("unsupported view class $viewClass")
            }
        }
        return view as T
    }


}