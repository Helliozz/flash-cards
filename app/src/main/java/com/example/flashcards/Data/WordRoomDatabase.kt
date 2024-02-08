package com.example.flashcards.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database -> scope.launch { populateDatabase(database.wordDao()) } }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAll()
            wordDao.insertWord(
                Word(
                    engWord = "table",
                    rusWord = "стол",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "chair",
                    rusWord = "стул",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "book",
                    rusWord = "книга",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "window",
                    rusWord = "окно",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "door",
                    rusWord = "дверь",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "computer",
                    rusWord = "компьютер",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "phone",
                    rusWord = "телефон",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "pen", rusWord = "ручка", countOfLearning = 0, dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "pencil",
                    rusWord = "карандаш",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "bag", rusWord = "сумка", countOfLearning = 0, dateOfLastLearning = 0L
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

