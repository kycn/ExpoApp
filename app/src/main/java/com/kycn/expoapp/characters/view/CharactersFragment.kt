package com.kycn.expoapp.characters.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kycn.expoapp.R
import com.kycn.expoapp.characters.entity.CharacterItem
import com.kycn.expoapp.characters.viewmodel.CharactersViewModel
import com.kycn.expoapp.common.service.ApiResult
import com.kycn.expoapp.common.view.ViewFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters),
    CharactersView.CharactersViewListener {

    private val charactersViewModel : CharactersViewModel by viewModels()

    @Inject
    lateinit var viewFactory : ViewFactory

    private lateinit var charactersView: CharactersView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        charactersView = viewFactory.newInstance(CharactersView::class, null)
        return charactersView.getRoot()
    }

    override fun onStart() {
        super.onStart()
        charactersView.registerListener(this)

        charactersViewModel.characters.observe(this, {
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    charactersView.loading()
                }
                ApiResult.Status.SUCCESS -> {
                    charactersView.setCharacters(it.data?.results!!)
                }
                ApiResult.Status.ERROR -> {
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        charactersView.unregisterListener(this)
    }

    override fun onCharacterClicked(characterItem: CharacterItem) {
        Toast.makeText(activity, characterItem.name, Toast.LENGTH_SHORT).show()
    }
}