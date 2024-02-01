package com.example.flashcards.Data

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAll()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert (word:Word){
        Log.d("TRASH", "allWords is null = ${allWords.asLiveData().value == null}")
        wordDao.insertWord(word)
    }
    @WorkerThread
    suspend fun delete(word: Word){
        wordDao.delete(word)
    }

}