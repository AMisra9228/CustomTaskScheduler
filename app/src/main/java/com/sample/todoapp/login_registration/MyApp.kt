// MyApp.kt
package com.sample.todoapp

import android.app.Application
import com.sample.todoapp.di.AppComponent
import com.sample.todoapp.di.AppModule
import com.sample.todoapp.di.DaggerAppComponent

class MyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
