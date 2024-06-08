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

    fun addRecord(record: RecordMock) {
        MockRecordsDatabase.addRecord(record)
    }
}