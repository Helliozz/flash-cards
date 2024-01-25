package com.example.flashcards.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.flashcards.Data.WordData
import com.example.flashcards.R
import com.example.flashcards.ViewModel.MainActivityViewModel
import com.example.flashcards.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var score = 0
    private var id = 0
    private lateinit var binding: FragmentGameBinding
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var words: List<WordData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        binding.score.text = score.toString()
        binding.right.isEnabled = false
        binding.wrong.isEnabled = false
        words = mainActivityViewModel.getWords().shuffled()
        binding.engWord.text = mainActivityViewModel.setWord(words, id, words.size)?.engWord
        binding.rusWord.text = mainActivityViewModel.setWord(words, id, words.size)?.rusWord
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.back.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_gameFragment_to_mainScreenFragment)
        }
        binding.wordCard.setOnClickListener {
            binding.rusWord.isVisible = true
            binding.right.isEnabled = true
            binding.wrong.isEnabled = true
        }

        binding.right.setOnClickListener {
            score++
            binding.score.text = score.toString()
            checkStatus()
        }
        binding.wrong.setOnClickListener {
            checkStatus()
        }
    }

    private fun checkStatus() {
        id++
        binding.right.isEnabled = false
        binding.wrong.isEnabled = false
        if (mainActivityViewModel.setWord(words, id, words.size) == null) {
            binding.wordCard.isVisible = false
            mainActivityViewModel.addSessionScore(score)
        } else {
            binding.rusWord.isVisible = false
            binding.rusWord.text = mainActivityViewModel.setWord(words, id, words.size)?.rusWord
            binding.engWord.text = mainActivityViewModel.setWord(words, id, words.size)?.engWord
        }
    }
}
