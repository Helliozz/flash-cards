package com.example.flashcards.ViewModel

import androidx.lifecycle.ViewModel
import com.example.flashcards.Data.Word

class MainActivityViewModel : ViewModel() {

    private var sessionScore = 0

    fun setWord(words: List<Word>, id: Int, size: Int?): Word? {
        return if (size == null) {
            null
        } else if (id < size) {
            words[id]
        } else {
            null
        }
    }
}