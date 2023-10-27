package com.siljan.istriaevents.ui.onboarding

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.siljan.istriaevents.R
import com.siljan.istriaevents.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.gender_option_list, R.layout.spinner_item)

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        binding.signupGenderSpinner.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}