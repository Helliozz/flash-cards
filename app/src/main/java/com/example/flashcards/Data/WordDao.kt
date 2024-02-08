package com.example.flashcards.Data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY eng_word ASC")
    fun getAll(): Flow<List<Word>>

    @Query("SELECT * FROM word_table WHERE count_of_learning < 7 ORDER BY eng_word ASC")
    fun getActiveWords(): Flow<List<Word>>

    @Query("SELECT * FROM word_table WHERE count_of_learning >= 7 ORDER BY eng_word ASC")
    fun getDisableWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(vararg words: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(word: Word)


}