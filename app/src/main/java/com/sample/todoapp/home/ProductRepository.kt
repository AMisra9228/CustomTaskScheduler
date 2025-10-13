package com.sample.todoapp.home

import com.sample.todoapp.data.ProductDataStorage

class ProductRepository {
    var productDataStorage = ProductDataStorage()

    suspend fun addProductsToList() : List<Product> = productDataStorage.addProductsToList()


}