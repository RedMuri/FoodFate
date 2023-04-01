package com.example.data.repository

import com.example.data.exceptions.UserAlreadyExistsException
import com.example.data.exceptions.UserDoesNotExistsException
import com.example.data.exceptions.UserNotFoundException
import com.example.data.exceptions.WrongPasswordLogInException
import com.example.data.mappers.UsersMapper
import com.example.db.dao.UsersDao
import com.example.domain.model.User
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val usersMapper: UsersMapper,
) : com.example.domain.repository.UsersRepository {

    @Throws(UserAlreadyExistsException::class)
    override suspend fun signIn(user: User, callback: () -> Unit) {
        val existingUser = usersDao.getUserByPhone(user.phone)
        if (existingUser == null) {
            usersDao.addUser(usersMapper.userEntityToDbModel(user))
            callback.invoke()
        } else throw UserAlreadyExistsException()
    }

    @Throws(UserDoesNotExistsException::class, WrongPasswordLogInException::class)
    override suspend fun logIn(phone: String, password: String, callback: () -> Unit) {
        val existingUser = usersDao.getUserByPhone(phone)
        if (existingUser != null) {
            if (existingUser.password == password) {
                callback.invoke()
            } else throw WrongPasswordLogInException()
        } else throw UserDoesNotExistsException()
    }
}