package com.kycn.expoapp.characters.usecase

import com.kycn.expoapp.characters.repository.CharactersRepositoryImpl
import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepositoryImpl: CharactersRepositoryImpl) {
    fun getCharacters() : Flow<ApiResult<CharactersResult>> {
        return flow {
            charactersRepositoryImpl.getCharacters()
                    .collect { result ->
                        emit(result)
                    }
        }
    }
}