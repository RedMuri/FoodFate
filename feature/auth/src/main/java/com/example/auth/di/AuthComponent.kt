package com.example.auth.di

import android.app.Application
import com.example.auth.fragments.LogInFragment
import com.example.auth.fragments.SignInFragment
import com.example.data.di.DataModule
import com.example.db.di.DbModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DataModule::class, DbModule::class]
)
interface AuthComponent {

    fun inject(signInFragment: SignInFragment)

    fun inject(logInFragment: LogInFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application,
        ): AuthComponent
    }
}
