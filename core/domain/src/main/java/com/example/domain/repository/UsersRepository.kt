package com.example.domain.repository

import com.example.domain.model.User

interface UsersRepository {

    suspend fun signIn(user: User, callback: () -> Unit)

    suspend fun logIn(phone: String, password: String, callback: () -> Unit)
}