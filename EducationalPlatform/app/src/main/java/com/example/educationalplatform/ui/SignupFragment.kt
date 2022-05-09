package com.example.educationalplatform.ui

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
import com.example.educationalplatform.databinding.FragmentSignupBinding
import com.example.educationalplatform.data.api.model.UserForm
import com.example.educationalplatform.respository.AuthRepository
import com.example.educationalplatform.view_model.AuthViewModel
import com.example.educationalplatform.view_model.AuthViewModelFactory

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        configureViewModel()

        viewModel.signupResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Registration completed! Now please login.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        binding.signupButton.setOnClickListener {
            val userForm = getUserForm()
            Log.e(SignupFragment::class.java.simpleName, "${userForm.username}, ${userForm.email}, ${userForm.password1}, ${userForm.password2}")
            viewModel.signup(userForm)
        }
        return binding.root
    }

    private fun configureViewModel() {
        val service = createAuthService()
        val repository = AuthRepository(service)
        val viewModelFactory = AuthViewModelFactory(repository = repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

    private fun getUserForm(): UserForm {
        return UserForm(
            username = binding.usernameEditText.text.toString(),
            email = binding.emailEditText.text.toString(),
            password1 = binding.passwordEditText.text.toString(),
            password2 = binding.passwordConfirmationEditText.text.toString(),
        )
    }
}