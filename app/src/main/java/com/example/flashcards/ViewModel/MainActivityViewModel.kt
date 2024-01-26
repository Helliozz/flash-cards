package com.example.flashcards.ViewModel

import androidx.lifecycle.ViewModel
import com.example.flashcards.Data.WordData

class MainActivityViewModel : ViewModel() {
    private var arr: MutableList<WordData> = mutableListOf(
        WordData("English", "Английский"),
        WordData("Beer", "Пиво"),
        WordData("Data", "дата"),
        WordData("Add", "Добавить"),
        WordData("Game", "Игра"),
        WordData("Dictionary", "Словарь"),
        WordData("Fragment", "Фрагмент"),
        WordData("Button", "Кнопка"),
        WordData("Anonymous", "Анонимный")
    )
    private var sessionScore = 0
    fun getWords(): MutableList<WordData> {
        return arr
    }

    fun deleteWord(elem: WordData) {
        arr.remove(elem)
    }

    fun addWord(word: WordData) {
        arr.add(word)
    }

    fun setWord(words: List<WordData>, id: Int, size: Int): WordData? {
        return if (id < size) {
            words[id]
        } else {
            null
        }
    }

    fun getSessionScore(): Int {
        return sessionScore
    }

    fun addSessionScore(score: Int) {
        sessionScore += score
    }

}