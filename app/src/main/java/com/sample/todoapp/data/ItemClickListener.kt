package com.sample.todoapp.data

import com.sample.todoapp.data.entities.Item

interface ItemClickListener {
    fun onItemClick(item: Item)
    fun onItemDel(item: Item)
}