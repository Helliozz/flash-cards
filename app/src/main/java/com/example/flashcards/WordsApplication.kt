package com.example.flashcards

import android.app.Application
import com.example.flashcards.Data.AccountRepository
import com.example.flashcards.Data.AccountRoomDatabase
import com.example.flashcards.Data.WordRepository
import com.example.flashcards.Data.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    val wordApplicationScope = CoroutineScope(SupervisorJob())

    val wordDatabase by lazy { WordRoomDatabase.getDatabase(this, wordApplicationScope) }
    val wordRepository by lazy { WordRepository(wordDatabase.wordDao()) }

    val accountApplicationScope = CoroutineScope(SupervisorJob())

    val accountDatabase by lazy { AccountRoomDatabase.getDatabase(this, accountApplicationScope) }
    val accountRepository by lazy { AccountRepository(accountDatabase.accountDao()) }
}
