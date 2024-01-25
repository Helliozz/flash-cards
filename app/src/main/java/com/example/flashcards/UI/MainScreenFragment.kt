package com.example.flashcards.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.flashcards.R
import com.example.flashcards.ViewModel.MainActivityViewModel
import com.example.flashcards.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        binding.sessionScore.text=mainActivityViewModel.getSessionScore().toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.library.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_mainScreenFragment_to_dictionaryFragment)
        }
        binding.play.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_mainScreenFragment_to_gameFragment)
        }
    }
}