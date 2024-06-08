package com.rma.expensetracker.data.models

import com.rma.expensetracker.data.mock_database.MockCategoryDatabase
import com.rma.expensetracker.data.mock_database.MockUsersDatabase
import com.rma.expensetracker.data.models.mock.RecordMock
import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.data.models.raw.UserDto
import com.rma.expensetracker.data.models.useful.Record

fun mapUserDtoToUser(userDto: UserDto) : User {
    return User(
        userDto.userId,
        userDto.firstName,
        userDto.lastName,
        userDto.email,
        userDto.userName
    )
}

fun mapRecordMockListToRecordList(recordMockList: List<RecordMock>) : List<Record> {
    val recordList = mutableListOf<Record>()
    recordMockList.forEach { recordMock -> recordList.add(mapRecordMockToRecord(recordMock)) }
    return recordList
}

fun mapRecordMockToRecord(recordMock: RecordMock) : Record {
    return Record(
        id = recordMock.id,
        title = recordMock.title,
        amount = recordMock.amount,
        date = recordMock.date,
        isGroupRecord = recordMock.isGroupRecord,
        notes = recordMock.notes,
        photos = recordMock.photos,
        accountId = recordMock.accountId,
        user = MockUsersDatabase.getUserById(recordMock.userId),
        category = MockCategoryDatabase.getCategoryById(recordMock.categoryId)
    )
}