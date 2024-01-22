package com.example.flashcards.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flashcards.Adapter.DictionaryRecyclerViewAdapter
import com.example.flashcards.R
import com.example.flashcards.ViewModel.MainActivityViewModel
import com.example.flashcards.databinding.FragmentDictionaryBinding

class DictionaryFragment : Fragment() {


    private val recyclerViewAdapter by lazy { DictionaryRecyclerViewAdapter() }
    private lateinit var binding: FragmentDictionaryBinding
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addWord.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_dictionaryFragment_to_addWordFragment)
        }
        binding.back.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_dictionaryFragment_to_mainScreenFragment)
        }
        recyclerViewAdapter.differ.submitList(mainActivityViewModel.getWords())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerViewAdapter
        }
    }

}