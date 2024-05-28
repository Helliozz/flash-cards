package com.example.flashcards.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashcards.Data.Account
import com.example.flashcards.R
import com.example.flashcards.ViewModel.AccountViewModel
import com.example.flashcards.ViewModel.AccountViewModelFactory
import com.example.flashcards.ViewModel.MainActivityViewModel
import com.example.flashcards.WordsApplication
import com.example.flashcards.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val accountViewModel: AccountViewModel by viewModels {
        AccountViewModelFactory((activity!!.application as WordsApplication).accountRepository)
    }
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signIn.setOnClickListener {
            val user = Account(
                email = binding.email.text.toString(),
                password = binding.password.text.toString()
            )
            accountViewModel.accounts.observe(this) { accounts ->
                var same = false
                for (account in accounts) {
                    if (account.email == user.email && account.password == user.password) {
                        same = true
                    }
                }
                if (same) {
                    mainActivityViewModel.changeActiveAccount(user.email)
                    requireView().findNavController()
                        .navigate(R.id.action_signInFragment_to_mainScreenFragment)
                } else {
                    Toast.makeText(
                        context,
                        "Неправильно введена почта или пароль",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        binding.signUp.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }
}