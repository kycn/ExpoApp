package com.kycn.expoapp.characters.datasource

import com.kycn.expoapp.characters.service.CharactersApi
import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.common.datasource.BaseRemoteDataSource
import com.kycn.expoapp.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(private val charactersApi: CharactersApi)
    : BaseRemoteDataSource(), CharactersRemoteDataSource {
    override fun getCharacters(): Flow<ApiResult<CharactersResult>> {
        return getResultFromApi { charactersApi.getCharacters() }
    }
}