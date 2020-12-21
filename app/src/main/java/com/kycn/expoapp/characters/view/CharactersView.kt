package com.kycn.expoapp.characters.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kycn.expoapp.R
import com.kycn.expoapp.characters.entity.CharacterItem
import com.kycn.expoapp.common.view.BaseObservableViewImpl
import com.kycn.expoapp.common.view.ViewFactory

class CharactersView(
    mLayoutInflater: LayoutInflater,
    viewFactory: ViewFactory,
    @Nullable container: ViewGroup?
) : BaseObservableViewImpl<CharactersView.CharactersViewListener>(), CharactersAdapter.CharactersAdapterListener {

    interface CharactersViewListener {
        fun onCharacterClicked(characterItem: CharacterItem)
    }

    private var loadingBar: ProgressBar
    private var characters: RecyclerView
    private var charactersAdapter: CharactersAdapter

    init {
        setRoot(mLayoutInflater.inflate(R.layout.fragment_characters, container, false))

        loadingBar = findViewById(R.id.pb_loading)
        characters = findViewById(R.id.rv_characters)
        characters.layoutManager = LinearLayoutManager(getContext())
        setupRecyclerViewItemDivider()
        charactersAdapter = CharactersAdapter(this, viewFactory)
        characters.adapter = charactersAdapter
    }

    fun loading() {
        loadingBar.visibility = View.VISIBLE
    }

    fun setCharacters(characters: List<CharacterItem>) {
        loadingBar.visibility = View.GONE
        charactersAdapter.bindCharacters(characters)
    }

    private fun setupRecyclerViewItemDivider() {
        val itemDecorator = DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(
            ContextCompat.getDrawable(
                getContext()!!,
                R.drawable.divider_characters
            )!!
        )
        characters.addItemDecoration(itemDecorator)
    }

    override fun onCharacterClicked(characterItem: CharacterItem) {
        for (listener in getListeners())
            listener.onCharacterClicked(characterItem)
    }
}