package com.kycn.expoapp.characters.repository

import com.kycn.expoapp.characters.datasource.CharactersRemoteDataSourceImpl
import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val charactersRemoteDataSourceImpl: CharactersRemoteDataSourceImpl)
    : CharactersRepository {
    override fun getCharacters(): Flow<ApiResult<CharactersResult>> {
        return charactersRemoteDataSourceImpl.getCharacters()
    }
}