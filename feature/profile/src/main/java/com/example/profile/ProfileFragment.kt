package com.example.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import com.example.profile.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding == null")

    private val navigator by lazy {
        (requireActivity() as ProfileNavigation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindClickListeners()
    }

    private fun bindClickListeners() {
        binding.itemLogOut.setOnClickListener {
            updateSharedPreferences()
            navigator.navigateFromProfileToLogInPage()
        }
    }

    private fun updateSharedPreferences() {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        pref.edit().putBoolean(IS_LOGGED, false).apply()
    }


    companion object {

        const val IS_LOGGED = "is_logged"
    }
}