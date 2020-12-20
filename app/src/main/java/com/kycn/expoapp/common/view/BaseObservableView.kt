package com.kycn.expoapp.common.view

interface BaseObservableView<ListenerType> : BaseView {
    fun registerListener(listener: ListenerType)
    fun unregisterListener(listener: ListenerType)
}