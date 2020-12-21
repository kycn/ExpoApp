package com.kycn.expoapp.characters.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.characters.usecase.GetCharactersUseCase
import com.kycn.expoapp.common.service.ApiResult
import com.kycn.expoapp.common.viewmodel.BaseViewModel

class CharactersViewModel @ViewModelInject constructor(getCharactersUseCase: GetCharactersUseCase)
    : BaseViewModel() {
    private val _characters = MutableLiveData<ApiResult<CharactersResult>>()
    val characters: LiveData<ApiResult<CharactersResult>>
        get() = _characters

    init {
        fetchUnits(_characters) { getCharactersUseCase.getCharacters() }
    }

}