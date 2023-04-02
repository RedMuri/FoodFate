package com.example.mainpage.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.Eatery

class EateryDiffCallback : DiffUtil.ItemCallback<Eatery>() {

    override fun areItemsTheSame(oldItem: Eatery, newItem: Eatery): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Eatery, newItem: Eatery): Boolean =
        oldItem == newItem
}