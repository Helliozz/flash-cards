package com.example.flashcards.Data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table WHERE login == :login ORDER BY eng_word ASC")
    fun getAll(login: String): Flow<List<Word>>

    @Query("SELECT * FROM word_table WHERE login == :login AND count_of_learning < 7 ORDER BY eng_word ASC")
    fun getActiveWords(login: String): Flow<List<Word>>

    @Query("SELECT * FROM word_table WHERE login == :login AND count_of_learning >= 7 ORDER BY eng_word ASC")
    fun getDisableWords(login: String): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(vararg words: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(word: Word)
}

@Dao
interface AccountDao {
    @Query("SELECT * FROM account_table")
    fun getAllAccounts(): Flow<List<Account>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAccount(vararg account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)

    @Query("DELETE FROM account_table")
    suspend fun deleteAllAccounts()
}