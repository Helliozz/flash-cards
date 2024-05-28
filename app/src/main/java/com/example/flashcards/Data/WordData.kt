package com.example.flashcards.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "eng_word") val engWord: String,
    @ColumnInfo(name = "rus_word") val rusWord: String,
    @ColumnInfo(name = "count_of_learning") var countOfLearning: Int,
    @ColumnInfo(name = "date_of_last_learning") var dateOfLastLearning: Long,
    @ColumnInfo(name = "login") var login: String
)

@Entity(tableName = "account_table")
class Account(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
) {
    override fun toString(): String {
        return "id: $id\temail: $email\tpassword: $password"
    }
}