package com.kycn.expoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.kycn.expoapp.characters.viewmodel.CharactersViewModel
import com.kycn.expoapp.common.ApiResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val charactersViewModel : CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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