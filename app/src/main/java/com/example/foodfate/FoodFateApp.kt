package com.example.foodfate

import android.app.Application
import com.example.auth.di.AuthComponent
import com.example.auth.di.AuthComponentProvider

class FoodFateApp : Application(), AuthComponentProvider {

    val component by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getAuthComponent(): AuthComponent {
        return component
    }
}