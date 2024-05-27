package com.example.flashcards.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashcards.Data.Account
import com.example.flashcards.R
import com.example.flashcards.ViewModel.AccountViewModel
import com.example.flashcards.ViewModel.AccountViewModelFactory
import com.example.flashcards.WordsApplication
import com.example.flashcards.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val accountViewModel: AccountViewModel by viewModels {
        AccountViewModelFactory((activity!!.application as WordsApplication).accountRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.signUp.setOnClickListener {
            if ((binding.createPassword.text.toString() == binding.confirmPassword.text.toString()) && (binding.createPassword.text.toString()
                    .isNotEmpty())
            ) {
                accountViewModel.accounts.observe(this) { accounts ->
                    var i = 0
                    for (account in accounts) {
                        if (account.email == binding.email.text.toString()) {
                            Toast.makeText(
                                context,
                                "Пользователь с такой почтой уже существует",
                                Toast.LENGTH_SHORT
                            ).show()
                            i++
                        }
                    }
                    if (i == 0) {
                        accountViewModel.insert(
                            Account(
                                email = binding.email.text.toString(),
                                password = binding.createPassword.text.toString()
                            )
                        )
                        requireView().findNavController()
                            .navigate(R.id.action_signUpFragment_to_mainScreenFragment)
                    }
                }
            } else {
                Toast.makeText(context, "Введёный пароль не совпадает", Toast.LENGTH_SHORT).show()
            }

        }
        binding.signIn.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    override fun onDestroy() {
        accountViewModel.accounts.removeObservers(this)
        super.onDestroy()
    }
}