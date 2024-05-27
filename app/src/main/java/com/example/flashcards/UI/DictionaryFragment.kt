package com.example.flashcards.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flashcards.Adapter.DictionaryRecyclerViewAdapter
import com.example.flashcards.Data.Word
import com.example.flashcards.R
import com.example.flashcards.ViewModel.DictionaryViewModel
import com.example.flashcards.ViewModel.DictionaryViewModelFactory
import com.example.flashcards.WordsApplication
import com.example.flashcards.databinding.FragmentDictionaryBinding

class DictionaryFragment : Fragment() {
    private lateinit var deleteWord: (Word) -> Unit
    private val recyclerViewAdapter by lazy { DictionaryRecyclerViewAdapter(deleteWord) }
    private lateinit var binding: FragmentDictionaryBinding
    private val dictionaryViewModel: DictionaryViewModel by viewModels {
        DictionaryViewModelFactory(
            (activity!!.application as WordsApplication).wordRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        deleteWord = {
            dictionaryViewModel.delete(it)
        }

        binding.addWord.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_dictionaryFragment_to_addWordFragment)
        }
        binding.back.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_dictionaryFragment_to_mainScreenFragment)
        }

        dictionaryViewModel.activeWords.observe(activity!!) { words ->
            words.let {
                recyclerViewAdapter.differ.submitList(it)
            }
        }

        recyclerViewAdapter.differ.submitList(dictionaryViewModel.activeWords.value)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerViewAdapter
        }
    }

    override fun onDestroy() {
        dictionaryViewModel.activeWords.removeObservers(activity!!)
        super.onDestroy()
    }
}