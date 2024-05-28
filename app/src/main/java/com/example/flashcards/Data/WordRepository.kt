package com.example.flashcards.Data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {


    fun allWords(login:String): Flow<List<Word>> = wordDao.getAll(login)
    fun activeWords(login:String): Flow<List<Word>> = wordDao.getActiveWords(login)
    fun disableWords(login:String): Flow<List<Word>> = wordDao.getDisableWords(login)

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

class AccountRepository(private val accountDao: AccountDao) {
    val account: Flow<List<Account>> = accountDao.getAllAccounts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAccount(account: Account) {
        accountDao.insertAccount(account)
    }

    @WorkerThread
    suspend fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account)
    }
}