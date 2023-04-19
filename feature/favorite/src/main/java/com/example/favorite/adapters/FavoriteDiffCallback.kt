package com.example.favorite.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.Eatery

class FavoriteDiffCallback : DiffUtil.ItemCallback<Eatery>() {

    override fun areItemsTheSame(oldItem: Eatery, newItem: Eatery): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Eatery, newItem: Eatery): Boolean =
        oldItem == newItem
}