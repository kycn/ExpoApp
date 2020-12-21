package com.kycn.expoapp.common.datasource

import com.kycn.expoapp.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.Exception

open class BaseRemoteDataSource {

    protected fun <T> getResultFromApi(
        apiCall: suspend () -> Response<T>
    ) : Flow<ApiResult<T>> {
        return flow<ApiResult<T>> {
            try {
                val apiResult = apiCall.invoke()
                if (apiResult.isSuccessful) {
                    val body = apiResult.body()
                    if (body != null)
                        emit(ApiResult.success(body))
                } else {
                    emit(error("${apiResult.code()} ${apiResult.message()}"))
                }
            } catch (e: Exception) {
                emit(error(e.message ?: e.toString()))
            }
        }
    }

    private fun <T> error(message: String): ApiResult<T> {
        return ApiResult.error("Network call has failed for a following reason: $message")
    }
}