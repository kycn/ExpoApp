package com.kycn.expoapp.common.usecase

import com.kycn.expoapp.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

open class BaseUseCase  {

    protected fun <T> getResult(repositoryCall: () -> Flow<ApiResult<T>>): Flow<ApiResult<T>> {
        return flow {
            repositoryCall.invoke().collect{ result ->
                emit(result)
            }
        }
    }
}