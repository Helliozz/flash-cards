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
                    engWord = "Table",
                    rusWord = "Стол",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Chair",
                    rusWord = "Стул",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Book",
                    rusWord = "Книга",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Window",
                    rusWord = "Окно",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Door",
                    rusWord = "Дверь",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Computer",
                    rusWord = "Компьютер",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Phone",
                    rusWord = "Телефон",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Pen", rusWord = "Ручка", countOfLearning = 0, dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Pencil",
                    rusWord = "Карандаш",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "Bag", rusWord = "Сумка", countOfLearning = 0, dateOfLastLearning = 0L
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

