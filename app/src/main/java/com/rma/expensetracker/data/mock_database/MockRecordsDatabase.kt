package com.rma.expensetracker.data.mock_database

import com.rma.expensetracker.data.models.mock.RecordMock
import java.time.LocalDate

object MockRecordsDatabase {
    private var recordsList: MutableList<RecordMock> = mutableListOf(
        RecordMock(
            id = "anneOsobniRec1",
            title = "Stipendija",
            amount = 500.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            notes = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
                    "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "stipendijaAnne"
        ),
        RecordMock(
            id = "anneOsobniRec2",
            title = "Plaća",
            amount = 1000.00,
            date = LocalDate.now().minusDays(2),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "posaoAnne"
        ),
        RecordMock(
            id = "anneOsobniRec3",
            title = "Izlazak",
            amount = -500.00,
            date = LocalDate.now().minusDays(4),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "izlazakAnne"
        ),
        RecordMock(
            id = "anneOsobniRec4",
            title = "Plaća",
            amount = 670.00,
            date = LocalDate.now().minusDays(6),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "posaoAnne"
        ),
        RecordMock(
            id = "anneOsobniRec5",
            title = "Izlazak",
            amount = -40.00,
            date = LocalDate.now().minusDays(8),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "izlazakAnne"
        ),
        RecordMock(
            id = "anneOsobniRec6",
            title = "Plaća",
            amount = 780.00,
            date = LocalDate.now().minusDays(10),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "posaoAnne"
        ),
        RecordMock(
            id = "anneOsobniRec7",
            title = "Plaća",
            amount = 300.00,
            date = LocalDate.now().minusDays(12),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "posaoAnne"
        ),
        RecordMock(
            id = "anneOsobniRec8",
            title = "Stipendija",
            amount = 10.00,
            date = LocalDate.now().minusDays(14),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "stipendijaAnne"
        ),
        RecordMock(
            id = "anneOsobniRec9",
            title = "Izlazak",
            amount = -2000.00,
            date = LocalDate.now().minusDays(16),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "izlazakAnne"
        ),
        RecordMock(
            id = "anneOsobniRec10",
            title = "Stipendija",
            amount = 20.00,
            date = LocalDate.now().minusDays(18),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "stipendijaAnne"
        ),
        //////

        RecordMock(
            id = "peterOsobniRec1",
            title = "Plaća",
            amount = 1000.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "peterOsobniId",
            categoryId = "posaoPeter"
        ),
        RecordMock(
            id = "peterOsobniRec2",
            title = "Zabava",
            amount = -200.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "peterOsobniId",
            categoryId = "zabavaPeter"
        ),
        RecordMock(
            id = "peterOsobniRec3",
            title = "Restoran",
            amount = -300.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "peterOsobniId",
            categoryId = "restoranPeter"
        ),

        ///////

        RecordMock(
            id = "robertOsobniRec1",
            title = "Pelene",
            amount = -100.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "robertId",
            accountId = "robertOsobniId",
            categoryId = "dijeteRobert"
        ),
        RecordMock(
            id = "robertOsobniRec2",
            title = "Kauč",
            amount = -200.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "robertId",
            accountId = "robertOsobniId",
            categoryId = "kućaRobert"
        ),

        /////////
        RecordMock(
            id = "anneObiteljskiRec1",
            title = "Plaća",
            amount = 1000.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "annePeterObiteljskiId",
            categoryId = "posaoAnne"
        ),
        RecordMock(
            id = "peterObiteljskiRec1",
            title = "Plaća",
            amount = 1000.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "annePeterObiteljskiId",
            categoryId = "posaoPeter"
        ),
        RecordMock(
            id = "anneObiteljskiRec2",
            title = "Trosak",
            amount = -200.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "annePeterObiteljskiId",
            categoryId = "posaoAnne"
        ),
        RecordMock(
            id = "peterObiteljskiRec2",
            title = "Trosak",
            amount = -400.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "annePeterObiteljskiId",
            categoryId = "posaoPeter"
        ),

        ////////////////////
        RecordMock(
            id = "peterPoslovniRec1",
            title = "Investicija",
            amount = 10000.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "peterRobertPoslovniId",
            categoryId = "posaoPeter"
        ),
        RecordMock(
            id = "robertPoslovniRec1",
            title = "Investicija",
            amount = 3000.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "robertId",
            accountId = "peterRobertPoslovniId",
            categoryId = "posaoRobert"
        ),
    )

    fun getRecordById(recordId: String): RecordMock {
        return recordsList.first { it.id == recordId }
    }
    fun getRecordsByAccountId(accId: String): List<RecordMock> {
        return recordsList.filter { it.accountId == accId }
    }
    fun getRecordsByUserId(userId: String): List<RecordMock> {
        return recordsList.filter { it.userId == userId }
    }
    fun addRecord(record: RecordMock) {
        recordsList.add(record)
    }
    fun deleteRecord(recordId: String) {
        recordsList.removeIf {it.id == recordId}
    }

    fun updateRecord(recordId: String, newRecord: RecordMock) {
        val record = getRecordById(recordId)
        val updatedRecord = record.copy(
            title = newRecord.title,
            amount = newRecord.amount,
            date = newRecord.date,
            isGroupRecord = newRecord.isGroupRecord,
            notes = newRecord.notes,
            photos = newRecord.photos,
            userId = newRecord.userId,
            accountId = newRecord.accountId,
            categoryId =  newRecord.categoryId,
        )
        recordsList[recordsList.indexOf(record)] = updatedRecord
    }
}