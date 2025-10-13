// AppComponent.kt
package com.sample.todoapp.di

import com.sample.todoapp.login_registration.SignInActivity
import com.sample.todoapp.login_registration.SignUpActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: SignInActivity)
    fun inject(activity: SignUpActivity)
}
