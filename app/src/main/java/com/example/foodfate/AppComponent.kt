package com.example.foodfate

import android.app.Application
import com.example.data.di.DataModule
import com.example.db.di.DbModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DbModule::class, DataModule::class]
)
interface AppComponent {


    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application,
        ): AppComponent
    }
}