package com.example.foodfate

import android.app.Application
import com.example.auth.di.AuthComponent
import com.example.auth.fragments.LogInFragment
import com.example.auth.fragments.SignInFragment
import com.example.data.di.DataModule
import com.example.db.di.DbModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DbModule::class, DataModule::class]
)
interface AppComponent: AuthComponent {

    override fun inject(signInFragment: SignInFragment)

    override fun inject(logInFragment: LogInFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application,
        ): AppComponent
    }
}