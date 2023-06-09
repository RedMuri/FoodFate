package com.example.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cart.adapters.MealsAdapter
import com.example.cart.databinding.FragmentCartBinding
import com.example.domain.model.Meal

class CartFragment : Fragment() {


    private var _binding: FragmentCartBinding? = null
    private val binding: FragmentCartBinding
        get() = _binding ?: throw RuntimeException("FragmentCartBinding == null")

    private val adapterMeals by lazy {
        MealsAdapter()
    }

    private val navigator by lazy {
        (requireActivity() as CartNavigation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        bindClickListeners()
    }

    private fun bindClickListeners() {
        binding.btBuy.setOnClickListener {
            navigator.navigateFromCartToSuccessPage()
        }
    }

    private fun setupRecyclerView() {
        binding.rvMeals.adapter = adapterMeals
        val testMeals = listOf(
            Meal(1, "Набор «Биг Тейсти и фри»", 120, 200, 40),
            Meal(2, "Набор «Биг Тейсти и мал фри»", 160, 245, 25),
        )
        adapterMeals.submitList(testMeals)
    }

}