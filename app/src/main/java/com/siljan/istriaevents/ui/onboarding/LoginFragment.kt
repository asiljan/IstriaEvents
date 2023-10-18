package com.siljan.istriaevents.ui.onboarding

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.siljan.istriaevents.MainActivity
import com.siljan.istriaevents.common.BaseView
import com.siljan.istriaevents.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), BaseView<LoginIntent, LoginUIState> {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.states().observe(viewLifecycleOwner) {
            render(it)
        }

        binding.viewTextLabelSignup.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            view.findNavController().navigate(action)
        }

        binding.buttonSubmitLogin.setOnClickListener {
            viewModel.processIntent(
                LoginIntent.UserLogIn(
                    username = binding.inputTextUsername.text?.toString()?.trim() ?: "",
                    password = binding.inputTextPassword.text?.toString()?.trim() ?: ""
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun render(state: LoginUIState) {
        when(state) {
            LoginUIState.UserLoggedIn -> launchHome()
            is LoginUIState.UserLogInError -> {
                binding.inputTextUsernameLayout.isErrorEnabled = true
                binding.inputTextUsernameLayout.error = requireActivity().getString(state.reasonId)
            }
        }
    }

    private fun launchHome() {
        startActivity(MainActivity.getIntent(requireContext()))
        requireActivity().finish()
    }

}