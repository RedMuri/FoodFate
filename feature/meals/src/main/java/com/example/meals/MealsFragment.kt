package com.example.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.model.Meal
import com.example.meals.adapters.MealsAdapter
import com.example.meals.databinding.FragmentMealsBinding

class MealsFragment : Fragment() {


    private var _binding: FragmentMealsBinding? = null
    private val binding: FragmentMealsBinding
        get() = _binding ?: throw RuntimeException("FragmentMealsBinding == null")

    private val adapterMeals by lazy {
        MealsAdapter()
    }

    private val navigator by lazy {
        (requireActivity() as MealsNavigation)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        bindClickListeners()
    }

    private fun bindClickListeners() {
        binding.btGoToCart.setOnClickListener {
            navigator.navigateFromMealsToCartPage()
        }
    }

    private fun setupRecyclerView() {
        binding.rvMeals.adapter = adapterMeals
        val testMeals = listOf(
            Meal(1, "Набор «Биг Тейсти и фри»", 120,200,40),
            Meal(2, "Набор «Биг Тейсти и мал фри»", 160,245,25),
            Meal(3, "Набор «Биг Тейсти и фри»", 120,200,40),
            Meal(4, "Набор «Биг Тейсти и мал фри»", 160,245,25),
        )
        adapterMeals.submitList(testMeals)
    }

}