package com.example.flashcards.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class WordData(
    val id: Int,
    val engWord: String,
    val rusWord: String,
    var countOfLearning: Int = 0,
    var itWasLearningToday: Boolean = false
)

@Entity(tableName = "word_table")
class Word(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "eng_word") val engWord: String,
    @ColumnInfo(name = "rus_word") val rusWord: String,
    @ColumnInfo(name = "count_of_learning") var countOfLearning: Int,
    @ColumnInfo(name = "it_was_learning_today") var itWasLearningToday: Boolean
)