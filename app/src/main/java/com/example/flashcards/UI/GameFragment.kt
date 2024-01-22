package com.example.flashcards.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.flashcards.R
import com.example.flashcards.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var score = 100
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        binding.score.text = score.toString()
        binding.right.isEnabled = false
        binding.wrong.isEnabled = false
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
            binding.wordCard.isVisible = false
            score++
            binding.score.text = score.toString()
            binding.right.isEnabled = false
            binding.wrong.isEnabled = false
        }
        binding.wrong.setOnClickListener {
            binding.wordCard.isVisible = false
            binding.right.isEnabled = false
            binding.wrong.isEnabled = false
        }
    }

}
