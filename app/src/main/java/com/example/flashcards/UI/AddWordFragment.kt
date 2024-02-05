package com.example.flashcards.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashcards.Data.Word
import com.example.flashcards.Data.WordData
import com.example.flashcards.R
import com.example.flashcards.ViewModel.DictionaryViewModel
import com.example.flashcards.ViewModel.DictionaryViewModelFactory
import com.example.flashcards.ViewModel.MainActivityViewModel
import com.example.flashcards.WordsApplication
import com.example.flashcards.databinding.FragmentAddWordBinding
import java.util.Calendar


class AddWordFragment : Fragment() {

    private lateinit var binding: FragmentAddWordBinding

    private val dictionaryViewModel: DictionaryViewModel by viewModels {
        DictionaryViewModelFactory(
            (activity!!.application as WordsApplication).repository
        )
    }

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
            val word = Word(
                engWord = binding.engWord.text.toString(),
                rusWord = binding.rusWord.text.toString(),
                countOfLearning = 0,
                dateOfLastLearning = Calendar.getInstance().timeInMillis
            )
            dictionaryViewModel.insert(word)

            requireView().findNavController()
                .navigate(R.id.action_addWordFragment_to_dictionaryFragment)
        }
    }
}