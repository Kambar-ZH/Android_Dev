package com.example.educationalplatform.ui

import com.example.educationalplatform.model.Credentials
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
import com.example.educationalplatform.api.AuthService
import com.example.educationalplatform.api.createAuthService
import com.example.educationalplatform.databinding.FragmentCourseBinding
import com.example.educationalplatform.databinding.FragmentLoginBinding
import com.example.educationalplatform.globals.AppPreferences
import com.example.educationalplatform.model.UserForm
import com.example.educationalplatform.respository.AuthRepository
import com.example.educationalplatform.view_model.AuthViewModel
import com.example.educationalplatform.view_model.AuthViewModelFactory

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var repository: AuthRepository
    private lateinit var viewModel: AuthViewModel
    private lateinit var service: AuthService
    private lateinit var viewModelFactory: AuthViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        configureViewModel()

        viewModel.loginResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val authToken = response.body()
                if (authToken != null) {
                    AppPreferences.accessToken = authToken.access
                    AppPreferences.refreshToken = authToken.refresh
                }
                findNavController().navigate(R.id.action_loginFragment_to_courseFragment)
            } else {
                Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_SHORT).show()
            }
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
        service = createAuthService()
        repository = AuthRepository(service)
        viewModelFactory = AuthViewModelFactory(repository = repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

    private fun getCredentials(): Credentials {
        return Credentials(
            username = binding.usernameEditText.text.toString(),
            password = binding.passwordEditText.text.toString(),
        )
    }
}