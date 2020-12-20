package com.kycn.expoapp.characters.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.characters.usecase.GetCharactersUseCase
import com.kycn.expoapp.common.service.ApiResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharactersViewModel @ViewModelInject constructor(getCharactersUseCase: GetCharactersUseCase) : ViewModel() {
    private val _characters = MutableLiveData<ApiResult<CharactersResult>>()
    val characters: LiveData<ApiResult<CharactersResult>>
        get() = _characters

    init {
        viewModelScope.launch {
            _characters.postValue(ApiResult.loading())
            getCharactersUseCase.getCharacters().onStart {  }
                .collect {
                    _characters.postValue(it)
                }
        }
    }

}