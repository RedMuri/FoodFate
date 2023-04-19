package com.example.foodfate

import android.app.Application
import com.example.auth.di.AuthComponent
import com.example.auth.di.AuthComponentProvider
import com.yandex.mapkit.MapKitFactory

class FoodFateApp : Application(), AuthComponentProvider {

    private val component by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        super.onCreate()
    }

    override fun getAuthComponent(): AuthComponent {
        return component
    }

    companion object {

        private const val MAPKIT_API_KEY = "5f44d376-56e6-4bcd-b4e7-d7c9ee03c698"
    }
}