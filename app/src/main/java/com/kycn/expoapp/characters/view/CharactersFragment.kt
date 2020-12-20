package com.kycn.expoapp.characters.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kycn.expoapp.characters.viewmodel.CharactersViewModel
import com.kycn.expoapp.common.ApiResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val charactersViewModel : CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        charactersViewModel.characters.observe( this, {
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    Log.d("Kaya", "loading")
                }
                ApiResult.Status.SUCCESS -> {
                    Log.d("Kaya", it.data?.results.toString())
                }
                ApiResult.Status.ERROR -> {
                    Log.d("Kaya", it.message!!)
                }
            }
        })
    }
}