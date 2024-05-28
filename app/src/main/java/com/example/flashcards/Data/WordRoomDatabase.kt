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
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "chair",
                    rusWord = "стул",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "window",
                    rusWord = "окно",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "door",
                    rusWord = "дверь",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "computer",
                    rusWord = "компьютер",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "phone",
                    rusWord = "телефон",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "pen", rusWord = "ручка", countOfLearning = 0, dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "pencil",
                    rusWord = "карандаш",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "bag", rusWord = "сумка", countOfLearning = 0, dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "apple",
                    rusWord = "яблоко",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "car",
                    rusWord = "автомобиль",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "sun",
                    rusWord = "солнце",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "moon", rusWord = "луна", countOfLearning = 0, dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "star",
                    rusWord = "звезда",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "book",
                    rusWord = "книга",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "dog",
                    rusWord = "собака",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "cat", rusWord = "кот", countOfLearning = 0, dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "mouse",
                    rusWord = "мышь",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "bird",
                    rusWord = "птица",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "fish", rusWord = "рыба", countOfLearning = 0, dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "tree",
                    rusWord = "дерево",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "flower",
                    rusWord = "цветок",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "river",
                    rusWord = "река",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "sea", rusWord = "море", countOfLearning = 0, dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "ocean",
                    rusWord = "океан",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "mountain",
                    rusWord = "гора",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "hill", rusWord = "холм", countOfLearning = 0, dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "valley",
                    rusWord = "долина",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "desert",
                    rusWord = "пустыня",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "forest",
                    rusWord = "лес",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "sky", rusWord = "небо", countOfLearning = 0, dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "cloud",
                    rusWord = "облако",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "rain",
                    rusWord = "дождь",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "snow", rusWord = "снег", countOfLearning = 0, dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "wind",
                    rusWord = "ветер",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "storm",
                    rusWord = "буря",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
                )
            )
            wordDao.insertWord(
                Word(
                    engWord = "thunder",
                    rusWord = "гром",
                    countOfLearning = 0,
                    dateOfLastLearning = 0L,
                    login = "str@gmail.com"
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

@Database(entities = arrayOf(Account::class), version = 1, exportSchema = false)
abstract class AccountRoomDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    private class AccountDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database -> scope.launch { populateDatabase(database.accountDao()) } }
        }

        suspend fun populateDatabase(accountDao: AccountDao) {
            accountDao.deleteAllAccounts()
            accountDao.insertAccount(Account(email = "str@gmail.com", password = "123"))
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AccountRoomDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): AccountRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountRoomDatabase::class.java,
                    name = "account_database"
                ).addCallback(AccountDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

