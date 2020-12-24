package com.kycn.expoapp.utils

import com.kycn.expoapp.characters.entity.CharacterItem
import com.kycn.expoapp.characters.service.model.CharactersResult

object Constants {
    val CHARACTERS = CharactersResult(
        arrayListOf(
            CharacterItem(
                1, "test1", "test1", "test1", "test1", "test1"),
            CharacterItem(
                2, "test2", "test2", "test2", "test2", "test2")
        )
    )
}