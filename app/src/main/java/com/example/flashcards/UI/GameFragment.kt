package com.example.flashcards.UI

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashcards.Data.Word
import com.example.flashcards.R
import com.example.flashcards.ViewModel.DictionaryViewModel
import com.example.flashcards.ViewModel.DictionaryViewModelFactory
import com.example.flashcards.ViewModel.MainActivityViewModel
import com.example.flashcards.WordsApplication
import com.example.flashcards.databinding.FragmentGameBinding
import java.util.*

class GameFragment : Fragment() {

    private var score = 0
    private var id = 0
    private val dictionaryViewModel: DictionaryViewModel by viewModels {
        DictionaryViewModelFactory((activity!!.application as WordsApplication).repository)
    }
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentGameBinding
    private lateinit var words: MutableList<Word>
    private val currentDate = Calendar.getInstance()
    private var counterLog = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.d("Game", "game launched")
        binding = FragmentGameBinding.inflate(inflater, container, false)
        binding.score.text = score.toString()
        binding.right.isEnabled = false
        binding.wrong.isEnabled = false

        dictionaryViewModel.activeWords.observe(this) { wordsDB ->

            words = wordsDB.shuffled().toMutableList()

            Log.d("Game", "Time of observer = ${counterLog++}")
            for (i in wordsDB) {
                var mCal = Calendar.getInstance()
                mCal.timeInMillis = i.dateOfLastLearning
                if ((mCal.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR)) && (mCal.get(
                        Calendar.MONTH
                    ) == currentDate.get(Calendar.MONTH)) && (mCal.get(Calendar.DATE) == currentDate.get(
                        Calendar.DATE
                    ))
                ) {
                    Log.d(
                        "Game", "${i.engWord}, ${i.rusWord}, ${i.id}, ${i.countOfLearning}\n${
                            mCal.get(Calendar.YEAR)
                        } . ${mCal.get(Calendar.MONTH)} . ${mCal.get(Calendar.DATE)}\n${
                            currentDate.get(
                                Calendar.YEAR
                            )
                        } . ${currentDate.get(Calendar.MONTH)} . ${currentDate.get(Calendar.DATE)}\n${words.size}, $id"
                    )
                    words.remove(i)

                    Log.d("Game", "${words.size}, $id")

                }
            }



            if (mainActivityViewModel.setWord(words, id, words.size) == null) {
                binding.wordCard.isVisible = false
                mainActivityViewModel.addSessionScore(score)
            } else {
                binding.rusWord.isVisible = false
                binding.rusWord.text = mainActivityViewModel.setWord(words, id, words.size)?.rusWord
                binding.engWord.text = mainActivityViewModel.setWord(words, id, words.size)?.engWord
            }
            dictionaryViewModel.activeWords.removeObservers(this)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.back.setOnClickListener {
            if (mainActivityViewModel.setWord(words, id, words.size) != null) {
                mainActivityViewModel.addSessionScore(score)
            }
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
//            dictionaryViewModel.words.removeObservers(activity!!)

            dictionaryViewModel.update(mainActivityViewModel.setWord(words, id, words.size)!!
                .also { it.dateOfLastLearning = Calendar.getInstance().timeInMillis }
                .also { it.countOfLearning++ })
            checkStatus()
        }
        binding.wrong.setOnClickListener {
            words.add(mainActivityViewModel.setWord(words, id, words.size)!!)
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

    override fun onDestroy() {
        dictionaryViewModel.activeWords.removeObservers(activity!!)
        super.onDestroy()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mainActivityViewModel.setWord(words, id, words.size) != null) {
                    mainActivityViewModel.addSessionScore(score)
                }
                requireView().findNavController()
                    .navigate(R.id.action_gameFragment_to_mainScreenFragment)

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, callback
        )
    }
}
