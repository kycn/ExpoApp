package com.kycn.expoapp.characters.service

import com.kycn.expoapp.characters.service.model.CharactersResult
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(): Response<CharactersResult>
}