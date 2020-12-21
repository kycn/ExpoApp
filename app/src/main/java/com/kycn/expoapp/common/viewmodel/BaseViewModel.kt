package com.kycn.expoapp.common.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kycn.expoapp.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected fun <T> fetchUnits(
        unitType: MutableLiveData<ApiResult<T>>,
        useCaseCall: () -> Flow<ApiResult<T>>
    ) {
        viewModelScope.launch {
            unitType.postValue(ApiResult.loading())
            useCaseCall.invoke().onStart {  }
                .collect {
                    unitType.postValue(it)
                }
        }
    }
}