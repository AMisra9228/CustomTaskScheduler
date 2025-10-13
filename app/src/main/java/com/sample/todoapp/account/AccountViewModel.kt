package com.sample.todoapp.account

import androidx.lifecycle.ViewModel
import com.sample.todoapp.data.entities.Info
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: AccountRepository) : ViewModel() {

    fun insertData(userName: String, userMail: String) {
        val info = Info(userName, userMail)

        CoroutineScope(Dispatchers.IO).launch {
            repository.insert(info)
        }
    }
}