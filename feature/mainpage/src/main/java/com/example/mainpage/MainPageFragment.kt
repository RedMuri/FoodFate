package com.example.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mainpage.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {


    private var _binding: FragmentMainPageBinding? = null
    private val binding: FragmentMainPageBinding
        get() = _binding ?: throw RuntimeException("FragmentMainPageBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater,container,false)
        return binding.root
    }
}