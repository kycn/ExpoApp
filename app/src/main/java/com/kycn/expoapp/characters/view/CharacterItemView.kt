package com.kycn.expoapp.characters.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import com.kycn.expoapp.R
import com.kycn.expoapp.characters.entity.CharacterItem
import com.kycn.expoapp.common.view.BaseObservableViewImpl
import com.kycn.expoapp.common.view.GlideImageLoader

class CharacterItemView(
    mLayoutInflater: LayoutInflater,
    private val mImageLoader: GlideImageLoader,
    @Nullable container: ViewGroup?
) : BaseObservableViewImpl<CharacterItemView.CharacterListItemListener>() {

    interface CharacterListItemListener {
        fun onCharacterClicked(characterItem: CharacterItem)
    }

    private lateinit var characterItem : CharacterItem
    private var characterImage: ImageView
    private var characterName: TextView
    private var characterStatus: TextView
    private var characterSpecies: TextView
    private var characterGender: TextView

    init {
        setRoot(mLayoutInflater.inflate(R.layout.item_character, container, false))

        characterImage = findViewById(R.id.iv_character_image)
        characterName = findViewById(R.id.tv_character_name)
        characterStatus = findViewById(R.id.tv_status_val)
        characterSpecies = findViewById(R.id.tv_species_val)
        characterGender = findViewById(R.id.tv_gender_val)

        getRoot().setOnClickListener {
            for (listener in getListeners()) {
                listener.onCharacterClicked(characterItem)
            }
        }
    }

    fun bind(characterItem : CharacterItem) {
        this.characterItem = characterItem
        mImageLoader.loadUrl(characterItem.image, characterImage)
        characterName.text = characterItem.name
        characterStatus.text = characterItem.status
        characterSpecies.text = characterItem.species
        characterGender.text = characterItem.gender
    }
}