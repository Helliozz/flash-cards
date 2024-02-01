package com.example.flashcards.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.flashcards.Data.Word
import com.example.flashcards.Data.WordRepository
import kotlinx.coroutines.launch


class DictionaryViewModel (private val repository: WordRepository) : ViewModel(){
    val words: LiveData<List<Word>> = repository.allWords.asLiveData()



    fun insert (word:Word)=viewModelScope.launch {
        Log.d("TRASH", "added new word")
        repository.insert(word)
    }

    fun delete (word: Word)=viewModelScope.launch {
        repository.delete(word)
    }
}

class DictionaryViewModelFactory(private val repository: WordRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DictionaryViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DictionaryViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}