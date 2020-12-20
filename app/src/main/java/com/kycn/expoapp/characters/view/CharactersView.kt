package com.kycn.expoapp.characters.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kycn.expoapp.R
import com.kycn.expoapp.common.view.BaseObservableViewImpl

class CharactersView(
    mLayoutInflater: LayoutInflater,
    @Nullable container: ViewGroup?
) : BaseObservableViewImpl<CharactersView.CharactersViewListener>() {

    interface CharactersViewListener {
        fun onCharacterClicked(characterId: String)
    }

    private var loadingBar: ProgressBar
    private var characters: RecyclerView
    private var charactersAdapter: CharactersAdapter

    init {
        setRoot(mLayoutInflater.inflate(R.layout.fragment_characters, container, false))

        loadingBar = findViewById(R.id.pb_loading)
        characters = findViewById(R.id.rv_characters)
        characters.layoutManager = LinearLayoutManager(getContext())
        charactersAdapter = CharactersAdapter(mLayoutInflater)
        characters.adapter = charactersAdapter
    }


    class CharactersAdapter(
        private val layoutInflater: LayoutInflater
    ) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }


        class CharacterViewHolder(
            layoutInflater: LayoutInflater,
            parent: ViewGroup
        ) : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false)) {

        }
    }
}