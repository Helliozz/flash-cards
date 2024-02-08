package com.example.flashcards.Data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAll()
    val activeWords: Flow<List<Word>> = wordDao.getActiveWords()
    val disableWords: Flow<List<Word>> = wordDao.getDisableWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insertWord(word)
    }

    @WorkerThread
    suspend fun update(word: Word) {
        wordDao.update(word)
    }

    @WorkerThread
    suspend fun delete(word: Word) {
        wordDao.delete(word)
    }

}