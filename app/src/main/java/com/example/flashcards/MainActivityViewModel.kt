package com.example.flashcards

import androidx.lifecycle.ViewModel
import com.example.flashcards.Data.WordData
import java.nio.file.WatchEvent

class MainActivityViewModel:ViewModel() {
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
    fun getWords():MutableList<WordData>{
        return arr
    }
    fun addWord(word: WordData){
        arr.add(word)
    }
}