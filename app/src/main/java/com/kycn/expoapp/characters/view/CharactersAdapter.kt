package com.kycn.expoapp.characters.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kycn.expoapp.characters.entity.CharacterItem
import com.kycn.expoapp.common.view.ViewFactory

class CharactersAdapter(
    private val listener: CharactersAdapterListener,
    private val viewFactory: ViewFactory
) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>(), CharacterItemView.CharacterListItemListener {

    interface CharactersAdapterListener {
        fun onCharacterClicked(characterItem: CharacterItem)
    }

    private var characters: List<CharacterItem> = ArrayList()

    fun bindCharacters(characters: List<CharacterItem>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val characterItemView = viewFactory.newInstance(CharacterItemView::class, parent)
        characterItemView.registerListener(this)
        return CharacterViewHolder(characterItemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.characterItemView.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    override fun onCharacterClicked(characterItem: CharacterItem) {
        listener.onCharacterClicked(characterItem)
    }

    class CharacterViewHolder(
        internal val characterItemView: CharacterItemView
    ) : RecyclerView.ViewHolder(characterItemView.getRoot())
}