package com.kycn.expoapp.characters.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kycn.expoapp.R
import com.kycn.expoapp.characters.entity.CharacterItem
import com.kycn.expoapp.common.view.BaseObservableViewImpl
import com.kycn.expoapp.common.view.GlideImageLoader

class CharactersView(
    mLayoutInflater: LayoutInflater,
    mImageLoader: GlideImageLoader,
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
        setupRecyclerViewItemDivider()
        charactersAdapter = CharactersAdapter(mLayoutInflater, mImageLoader)
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

    class CharactersAdapter(
        private val layoutInflater: LayoutInflater,
        private val imageLoader: GlideImageLoader
    ) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

        private var characters: List<CharacterItem> = ArrayList()

        fun bindCharacters(characters: List<CharacterItem>) {
            this.characters = characters
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
            CharacterViewHolder(layoutInflater, parent, imageLoader)

        override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
            val character = characters[position]
            holder.bind(character)
        }

        override fun getItemCount(): Int = characters.size


        class CharacterViewHolder(
            layoutInflater: LayoutInflater,
            parent: ViewGroup,
            private val imageLoader: GlideImageLoader
        ) : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false)) {

            private var characterImage: ImageView = itemView.findViewById(R.id.iv_character_image)
            private var characterName: TextView = itemView.findViewById(R.id.tv_character_name)
            private var characterStatus: TextView = itemView.findViewById(R.id.tv_status_val)
            private var characterSpecies: TextView = itemView.findViewById(R.id.tv_species_val)
            private var characterGender: TextView = itemView.findViewById(R.id.tv_gender_val)

            fun bind(characterItem : CharacterItem) {
                imageLoader.loadUrl(characterItem.image, characterImage)
                characterName.text = characterItem.name
                characterStatus.text = characterItem.status
                characterSpecies.text = characterItem.species
                characterGender.text = characterItem.gender
            }
        }
    }
}