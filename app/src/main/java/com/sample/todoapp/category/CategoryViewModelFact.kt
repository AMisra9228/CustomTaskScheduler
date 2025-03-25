package com.sample.todoapp.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.todoapp.account.AccountRepository
import com.sample.todoapp.account.AccountViewModel

class CategoryViewModelFact (private val catagoryRepo: CategoryRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(catagoryRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}