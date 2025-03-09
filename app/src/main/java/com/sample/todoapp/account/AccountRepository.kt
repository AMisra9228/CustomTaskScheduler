package com.sample.todoapp.account

import com.sample.todoapp.data.dao.AccountDao
import com.sample.todoapp.data.entities.Info

class AccountRepository(private val accountDao: AccountDao)  {

    suspend fun insert(accinfo: Info) {
        accountDao.insertAccount(accinfo)
    }
}