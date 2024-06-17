package com.rma.expensetracker.data.models

import androidx.compose.ui.graphics.Color
import com.rma.expensetracker.data.interactors.CategoryInteractor
import com.rma.expensetracker.data.interactors.UserInteractor
import com.rma.expensetracker.data.mock_database.MockCategoryDatabase
import com.rma.expensetracker.data.mock_database.MockUsersDatabase
import com.rma.expensetracker.data.models.raw.account.AccountDto
import com.rma.expensetracker.data.models.raw.account.AccountWithGroupFlagDto
import com.rma.expensetracker.data.models.raw.authentication.UserDto
import com.rma.expensetracker.data.models.raw.category.CategoryDto
import com.rma.expensetracker.data.models.raw.record.Transaction
import com.rma.expensetracker.data.models.raw.user.UserGetAllDto
import com.rma.expensetracker.data.models.useful.Account
import com.rma.expensetracker.data.models.useful.Category
import com.rma.expensetracker.data.models.useful.Record
import com.rma.expensetracker.data.models.useful.RecordMock
import com.rma.expensetracker.data.models.useful.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun mapUserDtoToUser(userDto: UserDto): User {
    return User(
        userDto.userId,
        userDto.firstName,
        userDto.lastName,
        userDto.email,
        userDto.userName
    )
}

//// ACCOUNTS

fun mapAccountWithGroupFlagDtoToAccount(accWithGroup: AccountWithGroupFlagDto): Account {
    return Account(
        id = accWithGroup.accountID,
        title = accWithGroup.name,
        balance = accWithGroup.balance.toDouble(),
        isGroupAccount = accWithGroup.isGroupAccount.toInt() != 0
    )
}

fun mapAccountDtoToAccount(accDto: AccountDto, isGroupAccount: Boolean): Account {
    return Account(
        id = accDto.accountID,
        title = accDto.name,
        balance = accDto.balance.toDouble(),
        isGroupAccount = isGroupAccount
    )
}

fun mapAccountDtoListToAccountList(
    accDtoList: List<AccountDto>,
    isGroupAccount: Boolean
): List<Account> {
    val accList = mutableListOf<Account>()
    accDtoList.forEach { accDto -> accList.add(mapAccountDtoToAccount(accDto, isGroupAccount)) }
    return accList
}


/// RECORDS

fun mapRecordMockListToRecordList(recordMockList: List<RecordMock>): List<Record> {
    val recordList = mutableListOf<Record>()
    recordMockList.forEach { recordMock -> recordList.add(mapRecordMockToRecord(recordMock)) }
    return recordList
}

fun mapRecordMockToRecord(recordMock: RecordMock): Record {
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

fun mapTransactionToRecord(transaction: Transaction, user: User, category: Category): Record {
    return Record(
        id = transaction.uniqueID,
        title = transaction.title,
        amount = if(transaction.type.toInt() == 0){
            -transaction.amount.toDouble()
        } else {
            transaction.amount.toDouble()
        },
        date = stringToLocalDate(transaction.dateOfInsertion),
        isGroupRecord = transaction.isParent.toInt() != 0,
        notes = transaction.description,
        accountId = transaction.accountID,
        user = user,
        category = category
    )
}

suspend fun mapTransactionsListToRecordsList(transactions: List<Transaction>): List<Record>? {
    val recordsList = mutableListOf<Record>()
    transactions.forEach { transaction ->
            val user = UserInteractor.getUserById(transaction.userID)
            val category = CategoryInteractor.getCategoryById(transaction.categoryID)

            if (user == null || category == null)
                return null
            else
                recordsList.add(mapTransactionToRecord(transaction, user, category))
    }
    return recordsList
}

private fun stringToLocalDate(dateString: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return try {
        LocalDate.parse(dateString, formatter)
    } catch (e: DateTimeParseException) {
        println(e.message)
        LocalDate.now()
    }
}

//// CATEGORIES
fun mapCategoryDtoToCategory(categoryDto: CategoryDto): Category {
    return Category(
        id = categoryDto.uniqueID,
        title = categoryDto.title,
        color = Color(categoryDto.color.toInt()),
        userId = categoryDto.userID
    )
}

fun mapCategoryDtoListToCategoryList(categoryDtoList: List<CategoryDto>): List<Category> {
    val categoryList = mutableListOf<Category>()
    categoryDtoList.forEach {
        categoryDto -> categoryList.add(mapCategoryDtoToCategory(categoryDto))
    }
    return categoryList
}


//// USERS
fun mapUserGetAllDtoToUser(userDto: UserGetAllDto): User {
    return User(
        id = userDto.userID,
        firstName = userDto.name,
        lastName = userDto.surname,
        email = userDto.email,
        userName = userDto.username
    )
}

fun mapUserGetAllDtoListToUserList(userDtoList: List<UserGetAllDto>): List<User> {
    val usersList = mutableListOf<User>()
    userDtoList.forEach {
            userDto -> usersList.add(mapUserGetAllDtoToUser(userDto))
    }
    return usersList
}