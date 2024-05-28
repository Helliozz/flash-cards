package com.example.flashcards.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.flashcards.Data.Account
import com.example.flashcards.Data.AccountRepository
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: AccountRepository) : ViewModel() {
    val accounts: LiveData<List<Account>> = repository.account.asLiveData()

    fun insert(account: Account) = viewModelScope.launch {
        repository.insertAccount(account)
    }

    fun delete(account: Account) = viewModelScope.launch {
        repository.deleteAccount(account)
    }
}

class AccountViewModelFactory(private val repository: AccountRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AccountViewModel::class.java)){
            @Suppress("UNCHECKED_CAST") return AccountViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}