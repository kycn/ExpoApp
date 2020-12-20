package com.kycn.expoapp.characters.datasource

import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.common.service.ApiResult
import kotlinx.coroutines.flow.Flow

interface CharactersRemoteDataSource {
    fun getCharacters() : Flow<ApiResult<CharactersResult>>
}