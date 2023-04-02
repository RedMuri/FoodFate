package com.example.domain.use_cases

import com.example.domain.repository.UsersRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(name: String, password: String, callback: () -> Unit) =
        usersRepository.logIn(name, password, callback)
}