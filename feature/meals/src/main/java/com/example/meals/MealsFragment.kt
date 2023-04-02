package com.example.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.meals.databinding.FragmentMealsBinding

class MealsFragment : Fragment() {


    private var _binding: FragmentMealsBinding? = null
    private val binding: FragmentMealsBinding
        get() = _binding ?: throw RuntimeException("FragmentMealsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

}