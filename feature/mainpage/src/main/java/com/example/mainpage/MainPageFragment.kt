package com.example.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.model.Eatery
import com.example.mainpage.adapters.EateriesAdapter
import com.example.mainpage.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {


    private var _binding: FragmentMainPageBinding? = null
    private val binding: FragmentMainPageBinding
        get() = _binding ?: throw RuntimeException("FragmentMainPageBinding == null")

    private val adapterEateries by lazy {
        EateriesAdapter()
    }

    private val navigator by lazy {
        (requireActivity() as MainPageNavigation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        bindClickListeners()
    }

    private fun bindClickListeners() {
        adapterEateries.onBtRightArrowClickListener = {
            navigator.navigateFromMainPageToMealsPage()
        }
    }

    private fun setupRecyclerView() {
        binding.rvEateries.adapter = adapterEateries
        val testEateries = listOf(
            Eatery(1, "Ресторан «Один»", "С 9:00 до 18:00", "4,8"),
            Eatery(2, "Ресторан «Два»", "С 9:00 до 18:00", "4,7"),
            Eatery(3, "Ресторан «Три»", "С 9:00 до 18:00", "4,7"),
            Eatery(4, "Ресторан «Четыре»", "С 9:00 до 18:00", "4,6"),
            Eatery(5, "Ресторан «Пять»", "С 9:00 до 18:00", "4,5"),
        )
        adapterEateries.submitList(testEateries)
    }
}