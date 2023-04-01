package com.example.mainpage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.Eatery
import com.example.mainpage.databinding.RvItemEateryBinding

class EateriesAdapter() :
    ListAdapter<Eatery, EateriesAdapter.EateryViewHolder>(
        EateryDiffCallback()
    ) {

    var onItemClickListener: ((Eatery) -> Unit)? = null

    class EateryViewHolder(val binding: RvItemEateryBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EateryViewHolder {
        val binding = RvItemEateryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EateryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EateryViewHolder, position: Int) {
        with(holder.binding) {
            with(currentList[position]) {
                tvEateryName.text = name
                tvEateryRating.text = rating
                tvEaterySchedule.text = schedule
                root.setOnClickListener {
                    onItemClickListener?.invoke(this)
                }
            }
        }
    }
}
