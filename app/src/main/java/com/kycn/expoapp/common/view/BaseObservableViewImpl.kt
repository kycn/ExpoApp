package com.kycn.expoapp.common.view

import java.util.*
import java.util.concurrent.ConcurrentHashMap

open class BaseObservableViewImpl<ListenerType> : BaseViewImpl(), BaseObservableView<ListenerType> {

    private val mListeners: MutableSet<ListenerType> = Collections.newSetFromMap(ConcurrentHashMap(0))

    override fun registerListener(listener: ListenerType) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        mListeners.remove(listener)
    }

    fun getListeners() = mListeners
}