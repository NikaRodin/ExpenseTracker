package com.rma.expensetracker.data.interactors

import com.rma.expensetracker.common.Constants.apiService
import com.rma.expensetracker.data.models.mapTransactionToRecord
import com.rma.expensetracker.data.models.mapTransactionsListToRecordsList
import com.rma.expensetracker.data.models.raw.account.AccountID
import com.rma.expensetracker.data.models.raw.account.UserID
import com.rma.expensetracker.data.models.raw.record.AddRecordRequest
import com.rma.expensetracker.data.models.raw.record.DeleteRecordRequest
import com.rma.expensetracker.data.models.raw.record.GetRecordByIdRequest
import com.rma.expensetracker.data.models.raw.record.GetRecordsByAccountIdRequest
import com.rma.expensetracker.data.models.raw.record.GetRecordsByUserIdRequest
import com.rma.expensetracker.data.models.raw.record.TransactionID
import com.rma.expensetracker.data.models.raw.record.TransactionToAdd
import com.rma.expensetracker.data.models.raw.record.TransactionToUpdate
import com.rma.expensetracker.data.models.raw.record.UpdateRecordRequest
import com.rma.expensetracker.data.models.useful.Record
import com.rma.expensetracker.data.models.useful.RecordMock
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.absoluteValue

object RecordInteractor {
    suspend fun getRecordById(recordId: String): Record? {
        val request = GetRecordByIdRequest(
            operation = "get_transaction_for_transaction_id",
            transaction = TransactionID(recordId)
        )

        try {
            val response = apiService.getRecordById(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    val user = UserInteractor.getUserById(responseBody.transaction.userID)
                    val category = CategoryInteractor.getCategoryById(responseBody.transaction.categoryID)

                    if (user == null || category == null)
                        null
                    else
                        mapTransactionToRecord(responseBody.transaction, user, category)
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                    null
                }
            } else {
                println("Error: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return null
        }
    }

    suspend fun getRecordsByAccountId(accId: String): List<Record> {
        val finalRecords = mutableListOf<Record>()

        val request = GetRecordsByAccountIdRequest(
            operation = "get_transactions_for_account",
            account = AccountID(accId)
        )

        try {
            val response = apiService.getRecordsByAccountId(request)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    val recordsList = mapTransactionsListToRecordsList(responseBody.transactions)
                    if(recordsList != null) finalRecords.addAll(recordsList)
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                }
            } else {
                println("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
        }

        return finalRecords
    }

    suspend fun getRecordsByUserId(userId: String): List<Record> {
        val finalRecords = mutableListOf<Record>()

        val request = GetRecordsByUserIdRequest(
            operation = "get_transactions_for_user",
            user = UserID(userId)
        )

        try {
            val response = apiService.getRecordsByUserId(request)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    val recordsList = mapTransactionsListToRecordsList(responseBody.transactions)
                    if(recordsList != null) finalRecords.addAll(recordsList)
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                }
            } else {
                println("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
        }

        return finalRecords
    }

    suspend fun addRecord(record: RecordMock): Boolean {
        //MockAccountInteractor.updateBalance(record.accountId) ->> automatski

        val request = AddRecordRequest(
            operation = "insert_transaction",
            transaction = TransactionToAdd(
                accountID = record.accountId,
                amount = record.amount.absoluteValue,
                base64Photo = "slika",
                categoryId = record.categoryId,
                currencyId = "6653764ab60e4",
                dateOfInsertion = localDateToString(record.date),
                description = record.notes?: "",
                label = "labela",
                title = record.title,
                transactionType = if(record.amount >= 0) 1 else 0,
                userId = record.userId
            )
        )

        try {
            val response = apiService.addRecord(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    true
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                    false
                }
            } else {
                println("Error: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return false
        }
    }

    suspend fun deleteRecord(recordId: String): Boolean {
        /*val accId = getRecordById(recordId).accountId
        MockRecordsDatabase.deleteRecord(recordId)
        MockAccountInteractor.updateBalance(accId)
        ->> automatski */

        val request = DeleteRecordRequest(
            operation = "delete_transaction",
            transaction = TransactionID(recordId)
        )

        try {
            val response = apiService.deleteRecord(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    true
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                    false
                }
            } else {
                println("Error: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return false
        }
    }

    suspend fun updateRecord(recordId: String, newRecord: RecordMock): Boolean {
       /* val accId = getRecordById(recordId).accountId
        MockRecordsDatabase.updateRecord(recordId, newRecord)
        MockAccountInteractor.updateBalance(accId)
        MockAccountInteractor.updateBalance(newRecord.accountId)*/

        val request = UpdateRecordRequest(
            operation = "update_transaction",
            transaction = TransactionToUpdate(
                transactionID = recordId,
                amount = newRecord.amount.absoluteValue,
                base64Photo = "slika",
                categoryId = newRecord.categoryId,
                currencyId = "6653764ab60e4",
                dateOfInsertion = localDateToString(newRecord.date),
                description = newRecord.notes?: "",
                label = "labela",
                title = newRecord.title,
                transactionType = if(newRecord.amount >= 0) 1 else 0,
                userId = newRecord.userId
            )
        )

        try {
            val response = apiService.updateRecord(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    true
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                    false
                }
            } else {
                println("Error: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return false
        }
    }
}

private fun localDateToString(date: LocalDate): String {
    return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}