// AppModule.kt
package com.sample.todoapp.di

import android.content.Context
import android.content.SharedPreferences
import com.sample.todoapp.data.TaskDatabase
import com.sample.todoapp.login_registration.UserDao
import com.sample.todoapp.login_registration.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton

    fun provideContext(): Context = context

    @Provides
    @Singleton

    fun provideDatabase(): TaskDatabase =
        TaskDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideUserDao(db: TaskDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository =
        UserRepository(userDao)


    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("UserLog", Context.MODE_PRIVATE)
    }

}
