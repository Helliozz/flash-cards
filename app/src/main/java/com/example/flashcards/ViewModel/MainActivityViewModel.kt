package com.example.flashcards.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.flashcards.Data.Word

class MainActivityViewModel : ViewModel() {
    private var activeAccount = ""

    fun setWord(words: List<Word>, id: Int, size: Int?): Word? {
        return if (size == null) {
            null
        } else if (id < size) {
            words[id]
        } else {
            null
        }
    }

    fun getActiveAccount():String{
        return activeAccount
    }
    fun changeActiveAccount(email:String){
        activeAccount=email
    }

}