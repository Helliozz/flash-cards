package com.example.flashcards.ViewModel

import androidx.lifecycle.ViewModel
import com.example.flashcards.Data.WordData

class MainActivityViewModel : ViewModel() {

    private var arr: MutableList<WordData> = mutableListOf(
        WordData(0, "English", "Английский"),
        WordData(1, "Beer", "Пиво"),
        WordData(2, "Data", "дата"),
        WordData(3, "Add", "Добавить"),
        WordData(4, "Game", "Игра"),
        WordData(5, "Dictionary", "Словарь"),
        WordData(6, "Fragment", "Фрагмент"),
        WordData(7, "Button", "Кнопка"),
        WordData(8, "Anonymous", "Анонимный")
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