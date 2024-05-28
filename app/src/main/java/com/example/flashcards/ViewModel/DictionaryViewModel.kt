package com.example.flashcards.ViewModel

import androidx.lifecycle.*
import com.example.flashcards.Data.Word
import com.example.flashcards.Data.WordDao
import com.example.flashcards.Data.WordRepository
import kotlinx.coroutines.launch

class DictionaryViewModel(private val repository: WordRepository) : ViewModel() {
    fun words(login:String): LiveData<List<Word>> = repository.allWords(login).asLiveData()
    fun activeWords(login:String): LiveData<List<Word>> = repository.activeWords(login).asLiveData()
    fun disableWords(login:String): LiveData<List<Word>> = repository.disableWords(login).asLiveData()
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun update(word: Word) = viewModelScope.launch {
        repository.update(word)
    }

    fun delete(word: Word) = viewModelScope.launch {
        repository.delete(word)
    }
}

class DictionaryViewModelFactory(private val repository: WordRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DictionaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return DictionaryViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}