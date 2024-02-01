package com.example.flashcards.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database -> scope.launch { populateDatabase(database.wordDao()) } }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAll()
            wordDao.insertWord(
                Word(
                    engWord = "Dick",
                    rusWord = "Член",
                    countOfLearning = 0,
                    itWasLearningToday = false
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Member",
                    rusWord = "Член",
                    countOfLearning = 0,
                    itWasLearningToday = false
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Penis",
                    rusWord = "Член",
                    countOfLearning = 0,
                    itWasLearningToday = false
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Cock",
                    rusWord = "Член",
                    countOfLearning = 0,
                    itWasLearningToday = false
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Tool",
                    rusWord = "Член",
                    countOfLearning = 0,
                    itWasLearningToday = false
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Organ",
                    rusWord = "Член",
                    countOfLearning = 0,
                    itWasLearningToday = false
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Prick",
                    rusWord = "Член",
                    countOfLearning = 0,
                    itWasLearningToday = false
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Willy",
                    rusWord = "Член",
                    countOfLearning = 0,
                    itWasLearningToday = false
                )
            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, WordRoomDatabase::class.java, "word_database"
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

