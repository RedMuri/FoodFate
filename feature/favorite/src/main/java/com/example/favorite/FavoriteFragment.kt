package com.example.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.model.Eatery
import com.example.favorite.adapters.FavoriteAdapter
import com.example.favorite.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoriteBinding == null")

    private val adapterFavorite by lazy {
        FavoriteAdapter()
    }

    private val navigator by lazy {
        (requireActivity() as FavoriteNavigation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        bindClickListeners()
    }

    private fun bindClickListeners() {
        adapterFavorite.onBtRightArrowClickListener = {
            navigator.navigateFromFavoriteToMealsPage()
        }
    }

    private fun setupRecyclerView() {
        binding.rvFavorite.adapter = adapterFavorite
        val testFavorite = listOf(
            Eatery(1, "Ресторан «Один»", "С 9:00 до 18:00", "4,8"),
            Eatery(2, "Ресторан «Два»", "С 9:00 до 18:00", "4,7")
        )
        adapterFavorite.submitList(testFavorite)
    }
}