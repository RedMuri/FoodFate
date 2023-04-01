package com.example.data.di

import com.example.data.repository.UsersRepositoryImpl
import com.example.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindUsersRepository(
        usersRepository: UsersRepositoryImpl,
    ): UsersRepository
}
