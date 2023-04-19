package com.example.favorite.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.Eatery
import com.example.favorite.databinding.RvItemFavoriteBinding

class FavoriteAdapter() :
    ListAdapter<Eatery, FavoriteAdapter.EateryViewHolder>(
        FavoriteDiffCallback()
    ) {

    var onBtRightArrowClickListener: ((Eatery) -> Unit)? = null

    class EateryViewHolder(val binding: RvItemFavoriteBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EateryViewHolder {
        val binding = RvItemFavoriteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EateryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EateryViewHolder, position: Int) {
        with(holder.binding) {
            with(currentList[position]) {
                tvEateryName.text = name
                tvEateryRating.text = rating
                tvEaterySchedule.text = schedule
                btRightArrow.setOnClickListener {
                    onBtRightArrowClickListener?.invoke(this)
                }
            }
        }
    }
}
