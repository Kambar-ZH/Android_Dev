package com.example.educationalplatform.ui.view

import com.example.educationalplatform.data.api.model.Credentials
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.educationalplatform.R
import com.example.educationalplatform.databinding.FragmentLoginBinding
import com.example.educationalplatform.view_model.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

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
            viewModel.login(credentials)
        }

        return binding.root
    }

    private fun getCredentials(): Credentials {
        return Credentials(
            username = binding.usernameEditText.text.toString(),
            password = binding.passwordEditText.text.toString(),
        )
    }
}