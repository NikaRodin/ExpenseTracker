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
            notes = "Stipendija za 6. mjesec.",
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
            title = "Auto",
            amount = -500.00,
            date = LocalDate.now().minusDays(4),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "autoAnne"
        ),
        RecordMock(
            id = "anneOsobniRec4",
            title = "Plaća",
            amount = 1000.00,
            date = LocalDate.now().minusDays(6),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "posaoAnne"
        ),

        RecordMock(
            id = "anneOsobniRec5",
            title = "Putovanje u Grčku",
            amount = -400.00,
            date = LocalDate.now().minusDays(8),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "odmorAnne"
        ),
        RecordMock(
            id = "anneOsobniRec6",
            title = "Stipendija",
            amount = 780.00,
            date = LocalDate.now().minusDays(10),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "stipendijaAnne"
        ),
        RecordMock(
            id = "anneOsobniRec7",
            title = "Povišica",
            amount = 600.00,
            date = LocalDate.now().minusDays(12),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "posaoAnne"
        ),
        RecordMock(
            id = "anneOsobniRec8",
            title = "Nova stolica",
            amount = -100.00,
            date = LocalDate.now().minusDays(14),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "domAnne"
        ),
        RecordMock(
            id = "anneOsobniRec9",
            title = "Ručak za dvoje",
            amount = -2000.00,
            date = LocalDate.now().minusDays(16),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "hranaAnne"
        ),
        RecordMock(
            id = "anneOsobniRec10",
            title = "Novi ormar",
            amount = -200.00,
            date = LocalDate.now().minusDays(18),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "anneOsobniId",
            categoryId = "domAnne"
        ),
        //////

        RecordMock(
            id = "peterOsobniRec1",
            title = "Plaća",
            amount = 1000.00,
            date = LocalDate.now().minusDays(2),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "peterOsobniId",
            categoryId = "posaoPeter"
        ),
        RecordMock(
            id = "peterOsobniRec2",
            title = "Zabava",
            amount = -200.00,
            date = LocalDate.now().minusDays(4),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "peterOsobniId",
            categoryId = "zabavaPeter"
        ),
        RecordMock(
            id = "peterOsobniRec3",
            title = "Putovanje u Švedsku",
            amount = -300.00,
            date = LocalDate.now().minusDays(6),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "peterOsobniId",
            categoryId = "putovanjePeter"
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
            date = LocalDate.now().minusDays(2),
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
            date = LocalDate.now().minusDays(2),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "annePeterObiteljskiId",
            categoryId = "posaoPeter"
        ),
        RecordMock(
            id = "anneObiteljskiRec2",
            title = "Obiteljsko putovanje",
            amount = -200.00,
            date = LocalDate.now().minusDays(4),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "annePeterObiteljskiId",
            categoryId = "odmorAnne"
        ),
        RecordMock(
            id = "peterObiteljskiRec2",
            title = "Odlazak u kino",
            amount = -400.00,
            date = LocalDate.now().minusDays(6),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "annePeterObiteljskiId",
            categoryId = "zabavaPeter"
        ),

        ////////////////////
        RecordMock(
            id = "annePoslovniRec1",
            title = "Nabavka sredstava",
            amount = -400.00,
            date = LocalDate.now(),
            isGroupRecord = false,
            userId = "anneId",
            accountId = "annePeterRobertPoslovniId",
            categoryId = "posaoPeter"
        ),
        RecordMock(
            id = "peterPoslovniRec1",
            title = "Investicija",
            amount = 10000.00,
            date = LocalDate.now().minusDays(2),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "annePeterRobertPoslovniId",
            categoryId = "posaoPeter"
        ),
        RecordMock(
            id = "robertPoslovniRec1",
            title = "Investicija",
            amount = 3000.00,
            date = LocalDate.now().minusDays(4),
            isGroupRecord = false,
            userId = "robertId",
            accountId = "annePeterRobertPoslovniId",
            categoryId = "posaoRobert"
        ),
        RecordMock(
            id = "peterPoslovniRec2",
            title = "Poslovni put",
            amount = -750.00,
            date = LocalDate.now().minusDays(6),
            isGroupRecord = false,
            userId = "peterId",
            accountId = "annePeterRobertPoslovniId",
            categoryId = "putovanjePeter"
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