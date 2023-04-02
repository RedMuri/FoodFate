package com.example.cart.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cart.databinding.RvCartItemMealBinding
import com.example.domain.model.Meal

class MealsAdapter() :
    ListAdapter<Meal, MealsAdapter.MealViewHolder>(
        MealDiffCallback()
    ) {

    class MealViewHolder(val binding: RvCartItemMealBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = RvCartItemMealBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        with(holder.binding) {
            with(currentList[position]) {
                tvMealName.text = name
                tvMealPrice.text = String.format("%s₽", price.toString())
                tvMealWeight.text = String.format("%s г.", weight.toString())
                tvMealDiscount.text = String.format("-%s%%", discount.toString())
            }
        }
    }
}
