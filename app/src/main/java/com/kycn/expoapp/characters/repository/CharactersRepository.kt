package com.kycn.expoapp.characters.repository

import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.common.ApiResult
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters() : Flow<ApiResult<CharactersResult>>
}