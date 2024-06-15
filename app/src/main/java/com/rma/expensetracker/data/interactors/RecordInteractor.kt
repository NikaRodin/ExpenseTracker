package com.rma.expensetracker.data.interactors

import com.rma.expensetracker.data.mock_database.MockRecordsDatabase
import com.rma.expensetracker.data.models.mapRecordMockListToRecordList
import com.rma.expensetracker.data.models.mapRecordMockToRecord
import com.rma.expensetracker.data.models.mock.RecordMock
import com.rma.expensetracker.data.models.useful.Record

object RecordInteractor {
    fun getRecordById(recordId: String): Record {
        return mapRecordMockToRecord(
            MockRecordsDatabase.getRecordById(recordId)
        )
    }

    fun getRecordsByAccountId(accId: String): List<Record> {
        return mapRecordMockListToRecordList(
            MockRecordsDatabase.getRecordsByAccountId(accId)
        )
    }

    fun getRecordsByUserId(userId: String): List<Record> {
        return mapRecordMockListToRecordList(
            MockRecordsDatabase.getRecordsByUserId(userId)
        )
    }

    fun addRecord(record: RecordMock) {
        MockRecordsDatabase.addRecord(record)
        AccountInteractor.updateBalance(record.accountId)
    }

    fun deleteRecord(recordId: String) {
        val accId = getRecordById(recordId).accountId
        MockRecordsDatabase.deleteRecord(recordId)
        AccountInteractor.updateBalance(accId)
    }

    fun updateRecord(recordId: String, newRecord: RecordMock) {
        val accId = getRecordById(recordId).accountId
        MockRecordsDatabase.updateRecord(recordId, newRecord)
        AccountInteractor.updateBalance(accId)
        AccountInteractor.updateBalance(newRecord.accountId)
    }
}