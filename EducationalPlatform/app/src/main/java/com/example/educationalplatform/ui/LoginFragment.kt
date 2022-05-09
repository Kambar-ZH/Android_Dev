package com.example.educationalplatform.ui

import com.example.educationalplatform.data.api.model.Credentials
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.educationalplatform.R
import com.example.educationalplatform.data.api.AuthService
import com.example.educationalplatform.data.api.createAuthService
import com.example.educationalplatform.databinding.FragmentLoginBinding
import com.example.educationalplatform.globals.AppPreferences
import com.example.educationalplatform.respository.AuthRepository
import com.example.educationalplatform.view_model.AuthViewModel
import com.example.educationalplatform.view_model.AuthViewModelFactory

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        configureViewModel()

        viewModel.loginResponse.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_loginFragment_to_courseFragment)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        binding.signupButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.loginButton.setOnClickListener {
            val credentials = getCredentials()
            Log.e(LoginFragment::class.java.simpleName, "${credentials.username}, ${credentials.password}")
            viewModel.login(credentials)
        }

        return binding.root
    }

    private fun configureViewModel() {
        val service = createAuthService()
        val repository = AuthRepository(service)
        val viewModelFactory = AuthViewModelFactory(repository = repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

    private fun getCredentials(): Credentials {
        return Credentials(
            username = binding.usernameEditText.text.toString(),
            password = binding.passwordEditText.text.toString(),
        )
    }
}