package com.example.auth.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.auth.*
import com.example.auth.databinding.FragmentLogInBinding
import com.example.auth.di.AuthComponentProvider
import com.example.auth.viewmodel.AuthViewModelFactory
import com.example.auth.viewmodel.LogInViewModel
import javax.inject.Inject


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding == null")

    private var errorToast: Toast? = null

    @Inject
    lateinit var authViewModelFactory: AuthViewModelFactory

    private val logInViewModel by lazy {
        ViewModelProvider(this, authViewModelFactory)[LogInViewModel::class.java]
    }

    private val navigator by lazy {
        (requireActivity() as AuthNavigation)
    }

    private val component by lazy {
        (requireActivity().application as AuthComponentProvider).getAuthComponent()
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        bindClickListeners()
        addTextChangeListeners()
    }

    private fun bindClickListeners() {
        binding.btLogIn.setOnClickListener {
            val firstName = binding.etPhone.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            logInViewModel.logIn(firstName, password)
        }
        binding.btSignIn.setOnClickListener{
            navigator.navigateFromLogInToSignInPage()
        }
    }

    private fun observeViewModel() {
        logInViewModel.authState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            binding.btLogIn.isEnabled = true

            when (it) {
                is AuthSuccess -> {
                    Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
                    navigator.navigateFromLogInToMainPage()
                    updateSharedPreferences()
                }
                is AuthProgress -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btLogIn.isEnabled = false
                }
                is AuthError -> {
                    showError(it.errorCode)
                }
            }
        }
    }

    private fun updateSharedPreferences() {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        pref.edit().putBoolean(IS_LOGGED, true).apply()
    }

    private fun showError(errorCode: Int) {
        when (errorCode) {
            LogInViewModel.ERROR_EMPTY_PHONE -> {
                showErrorEditText(binding.etPhone)
                showErrorToast("Все поля должны быть заполнены")
            }
            LogInViewModel.ERROR_EMPTY_PASSWORD -> {
                showErrorEditText(binding.etPassword)
                showErrorToast("Все поля должны быть заполнены")
            }
            LogInViewModel.ERROR_USER_DOES_NOT_EXISTS -> {
                showErrorEditText(binding.etPhone)
                showErrorToast("Пользователь с таким номером не существует")
            }
            LogInViewModel.ERROR_WRONG_PASSWORD -> {
                showErrorEditText(binding.etPassword)
                showErrorToast("Неверный пароль")
            }
        }
    }


    private fun addTextChangeListeners() {
        binding.etPhone.doOnTextChanged { _, _, _, _ ->
            hideErrorEditText(binding.etPhone)
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            hideErrorEditText(binding.etPassword)
        }
    }

    private fun hideErrorEditText(editText: EditText) {
        with(editText) {
            if (background != AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.bg_authorize_field_error
                )
            ) {
                background =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.bg_authorize_field)
                setHintTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray
                    )
                )
                setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray
                    )
                )
            }
        }
    }

    private fun showErrorEditText(editText: EditText) {
        with(editText) {
            if (background != AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.bg_authorize_field_error
                )
            ) {
                background =
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.bg_authorize_field_error
                    )
                setHintTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
                setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
            }
        }
    }

    private fun showErrorToast(errorText: String) {
        errorToast?.cancel()
        errorToast = Toast.makeText(
            requireContext(),
            errorText, Toast.LENGTH_SHORT
        )
        errorToast?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val IS_LOGGED = "is_logged"
    }
}
