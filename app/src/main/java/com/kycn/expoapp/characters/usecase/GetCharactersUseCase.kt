package com.kycn.expoapp.characters.usecase

import com.kycn.expoapp.characters.repository.CharactersRepositoryImpl
import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.common.service.ApiResult
import com.kycn.expoapp.common.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepositoryImpl: CharactersRepositoryImpl)
    : BaseUseCase() {
    fun getCharacters() : Flow<ApiResult<CharactersResult>> {
        return getResult { charactersRepositoryImpl.getCharacters() }
    }
}