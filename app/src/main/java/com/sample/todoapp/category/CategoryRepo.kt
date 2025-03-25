package com.sample.todoapp.category

import com.sample.todoapp.data.dao.ItemDao
import com.sample.todoapp.data.entities.Item

class CategoryRepo(private val itemDao: ItemDao)  {
    suspend fun insert(catinfo: Item) {
        itemDao.insertCategory(catinfo)
    }
}