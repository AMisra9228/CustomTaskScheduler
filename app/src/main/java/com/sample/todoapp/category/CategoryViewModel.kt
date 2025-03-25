package com.sample.todoapp.category

import androidx.lifecycle.ViewModel
import com.sample.todoapp.account.AccountRepository
import com.sample.todoapp.data.entities.Info
import com.sample.todoapp.data.entities.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepo) : ViewModel() {
    fun insertData(itemName: String, itemDes: String,itemPriority: String, itemDt: String)  {
        val item = Item(itemName, itemDes,itemPriority,itemDt)

        CoroutineScope(Dispatchers.IO).launch {
            repository.insert(item)
        }


    }
}