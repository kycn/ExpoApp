package com.kycn.expoapp.characters.datasource

import com.kycn.expoapp.characters.service.CharactersApi
import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(private val charactersApi: CharactersApi)
    : CharactersRemoteDataSource {
    override fun getCharacters(): Flow<ApiResult<CharactersResult>> {
        return flow<ApiResult<CharactersResult>> {
            try {
                val apiResult = charactersApi.getCharacters()
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