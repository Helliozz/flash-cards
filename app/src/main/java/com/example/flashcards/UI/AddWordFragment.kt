package com.example.flashcards.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.flashcards.Data.WordData
import com.example.flashcards.R
import com.example.flashcards.ViewModel.MainActivityViewModel
import com.example.flashcards.databinding.FragmentAddWordBinding


class AddWordFragment : Fragment() {

    private lateinit var binding: FragmentAddWordBinding
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.back.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_addWordFragment_to_dictionaryFragment)
        }
        binding.addWord.setOnClickListener {
            mainActivityViewModel.addWord(
                WordData(
                    binding.engWord.text.toString(),
                    binding.rusWord.text.toString()
                )
            )
            requireView().findNavController()
                .navigate(R.id.action_addWordFragment_to_dictionaryFragment)
        }
    }
}