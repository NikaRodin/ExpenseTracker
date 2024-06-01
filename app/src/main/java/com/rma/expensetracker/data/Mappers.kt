package com.rma.expensetracker.data

import com.rma.expensetracker.data.models.raw.UserDto
import com.rma.expensetracker.data.models.useful.User

fun mapUserDtoToUser(userDto: UserDto) : User {
    return User(userDto.firstName, userDto.lastName, userDto.email, userDto.userName)
}