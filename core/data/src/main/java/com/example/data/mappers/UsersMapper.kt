package com.example.data.mappers

import com.example.db.models.UserDbModel
import com.example.domain.model.User
import javax.inject.Inject

class UsersMapper @Inject constructor() {

    fun userDbModelToEntity(userDbModel: UserDbModel) =
        User(
            name = userDbModel.name,
            phone = userDbModel.phone,
            password = userDbModel.password
        )

    fun userEntityToDbModel(user: User) =
        UserDbModel(
            name = user.name,
            phone = user.phone,
            password = user.password
        )
}