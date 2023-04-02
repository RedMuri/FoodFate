package com.example.domain.use_cases

import com.example.domain.repository.UsersRepository
import com.example.domain.model.User
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(user: User, callback: () -> Unit) =
        usersRepository.signIn(user, callback)
}